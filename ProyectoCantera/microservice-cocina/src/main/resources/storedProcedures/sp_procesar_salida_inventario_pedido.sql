-- EXEC sp_procesar_salida_inventario_pedido 2, 'JABA'

ALTER PROCEDURE sp_procesar_salida_inventario_pedido
    @PedidoID INT,
    @cCodUsuario VARCHAR(50) = NULL
AS
BEGIN
    SET NOCOUNT ON;
    
    DECLARE @TipoMovimientoSalida INT
    DECLARE @ErrorMessage VARCHAR(500)
    
    -- Obtener el tipo de movimiento "Salida por Venta"
    SELECT @TipoMovimientoSalida = nTipoMovimientoInventarioId 
    FROM TipoMovimientoInventario 
    WHERE cDescripcion = 'Salida por Venta' AND bEstado = 1;
    
    IF @TipoMovimientoSalida IS NULL
    BEGIN
        SELECT 0 as Success, 'Tipo de movimiento "Salida por Venta" no encontrado' as Mensaje;
        RETURN;
    END
    
    -- Verificar que el pedido existe y está en estado válido para procesar
    IF NOT EXISTS (SELECT 1 FROM Pedidos WHERE PedidoID = @PedidoID AND Estado = 'Entregado')
    BEGIN
        SELECT 0 as Success, 'Pedido no encontrado o no está en estado Entregado' as Mensaje;
        RETURN
    END
    
    -- Verificar si ya se procesó el inventario para este pedido
    IF EXISTS (SELECT 1 FROM MovimientoInventario WHERE nPedidoID = @PedidoID)
    BEGIN
        SELECT 1 as Success, 'Inventario ya procesado para este pedido' as Mensaje;
        RETURN
    END
    
    BEGIN TRANSACTION
    
    BEGIN TRY
        -- Variables para el cursor
        DECLARE @ProductoID INT, @CantidadProducto INT;
        DECLARE @InsumoID INT, @CantidadInsumoRequerida DECIMAL(10,2);
        DECLARE @StockActual DECIMAL(10,2), @NuevoStock DECIMAL(10,2);
        DECLARE @NombreProducto VARCHAR(50), @NombreInsumo VARCHAR(100);
        DECLARE @CantidadTotalRequerida DECIMAL(10,2);
        
        -- *** USAR NOMBRES ÚNICOS PARA LOS CURSORES ***
        DECLARE productos_cursor_proc CURSOR FOR
        SELECT dp.ProductoID, dp.Cantidad, dp.NombreProducto
        FROM DetallePedido dp
        WHERE dp.PedidoID = @PedidoID;
        
        OPEN productos_cursor_proc;
        FETCH NEXT FROM productos_cursor_proc INTO @ProductoID, @CantidadProducto, @NombreProducto;
        
        WHILE @@FETCH_STATUS = 0
        BEGIN
            -- Para cada producto, obtener sus insumos de la receta
            DECLARE insumos_cursor_proc CURSOR FOR
            SELECT r.nInsumoId, r.nCantidadInsumo, i.cNombreInsumo, i.nStockActual
            FROM Receta r
            INNER JOIN Insumo i ON r.nInsumoId = i.nInsumoId
            WHERE r.nProductoId = @ProductoID AND i.bEstado = 1;
            
            OPEN insumos_cursor_proc;
            FETCH NEXT FROM insumos_cursor_proc INTO @InsumoID, @CantidadInsumoRequerida, @NombreInsumo, @StockActual;
            
            WHILE @@FETCH_STATUS = 0
            BEGIN
                -- Calcular cantidad total de insumo necesaria
                SET @CantidadTotalRequerida = @CantidadInsumoRequerida * @CantidadProducto;
                
                -- Verificar que hay suficiente stock
                IF @StockActual < @CantidadTotalRequerida
                BEGIN
                    SET @ErrorMessage = 'Stock insuficiente para ' + @NombreInsumo + 
                                      '. Disponible: ' + CAST(@StockActual AS VARCHAR(20)) + 
                                      ', Requerido: ' + CAST(@CantidadTotalRequerida AS VARCHAR(20));
                    
                    -- Cerrar cursores antes del error
                    CLOSE insumos_cursor_proc;
                    DEALLOCATE insumos_cursor_proc;
                    CLOSE productos_cursor_proc;
                    DEALLOCATE productos_cursor_proc;
                    
                    RAISERROR(@ErrorMessage, 16, 1);
                END
                
                -- Calcular nuevo stock
                SET @NuevoStock = @StockActual - @CantidadTotalRequerida;
                
                -- Actualizar stock en tabla Insumo
                UPDATE Insumo 
                SET nStockActual = @NuevoStock
                WHERE nInsumoId = @InsumoID;
                
                -- Registrar movimiento en historial
                INSERT INTO MovimientoInventario (
                    nTipoMovimientoInventarioId,
                    nInsumoId,
                    nCantidadMovimiento,
                    nStockAnterior,
                    nStockNuevo,
                    cObservaciones,
                    nPedidoID,
                    cCodUsuario
                ) VALUES (
                    @TipoMovimientoSalida,
                    @InsumoID,
                    -@CantidadTotalRequerida,
                    @StockActual,
                    @NuevoStock,
                    'Salida por venta - Producto: ' + @NombreProducto + ' (Cant: ' + CAST(@CantidadProducto AS VARCHAR(10)) + ')',
                    @PedidoID,
                    @cCodUsuario
                );
                
                FETCH NEXT FROM insumos_cursor_proc INTO @InsumoID, @CantidadInsumoRequerida, @NombreInsumo, @StockActual;
            END
            
            CLOSE insumos_cursor_proc;
            DEALLOCATE insumos_cursor_proc;
            
            FETCH NEXT FROM productos_cursor_proc INTO @ProductoID, @CantidadProducto, @NombreProducto;
        END
        
        CLOSE productos_cursor_proc;
        DEALLOCATE productos_cursor_proc;
        
        COMMIT TRANSACTION;
        
        SELECT 1 as Success, 'Inventario procesado correctamente' as Mensaje;
        
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        
        -- Limpiar cursores en caso de error
        IF CURSOR_STATUS('local','productos_cursor_proc') >= -1
        BEGIN
            CLOSE productos_cursor_proc;
            DEALLOCATE productos_cursor_proc;
        END
        
        IF CURSOR_STATUS('local','insumos_cursor_proc') >= -1
        BEGIN
            CLOSE insumos_cursor_proc;
            DEALLOCATE insumos_cursor_proc;
        END
        
        SELECT 0 as Success, ERROR_MESSAGE() as Mensaje;
    END CATCH
END