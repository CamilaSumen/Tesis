-- =============================================
-- CORRECCIÓN 2: SP para recibir orden de compra (cursor corregido)
-- =============================================

ALTER PROCEDURE sp_recibir_orden_compra
(
    @OrdenCompraId INT,
    @Usuario VARCHAR(100),
    @Observaciones VARCHAR(500) = NULL
)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @TipoMovimientoEntrada INT;
    DECLARE @EstadoActual INT;
    DECLARE @ProveedorNombre VARCHAR(100);

    -- Obtener el tipo de movimiento "Entrada por Compra"
    SELECT @TipoMovimientoEntrada = nTipoMovimientoInventarioId
    FROM TipoMovimientoInventario
    WHERE cDescripcion = 'Entrada por Compra' AND bEstado = 1;

    IF @TipoMovimientoEntrada IS NULL
    BEGIN
        SELECT 0 as Success, 'Tipo de movimiento "Entrada por Compra" no encontrado' as Mensaje;
        RETURN;
    END

    -- Verificar que la orden existe y está pendiente
    SELECT @EstadoActual = nEstadoOrdenCompraId
    FROM OrdenesCompra
    WHERE nOrdenesCompraId = @OrdenCompraId;

    IF @EstadoActual IS NULL
    BEGIN
        SELECT 0 as Success, 'Orden de compra no encontrada' as Mensaje;
        RETURN;
    END

    IF @EstadoActual != 1 -- 1 = Pendiente
    BEGIN
        SELECT 0 as Success, 'La orden de compra ya fue procesada o está anulada' as Mensaje;
        RETURN;
    END

    -- Obtener nombre del proveedor
    SELECT @ProveedorNombre = p.nNombreProveedor
    FROM OrdenesCompra oc
    INNER JOIN Proveedores p ON oc.nProveedorId = p.nProveedorId
    WHERE oc.nOrdenesCompraId = @OrdenCompraId;

    BEGIN TRANSACTION;

    BEGIN TRY
        -- Variables para el cursor (nombres únicos)
        DECLARE @InsumoId INT, @Cantidad DECIMAL(10,2), @PrecioUnitario DECIMAL(10,2);
        DECLARE @StockActual DECIMAL(10,2), @NuevoStock DECIMAL(10,2);
        DECLARE @NombreInsumo VARCHAR(100);
        DECLARE @CostoPromedioActual DECIMAL(10,3), @NuevoCostoPromedio DECIMAL(10,3);

        -- Cursor con nombre único para evitar conflictos
        DECLARE detalle_compra_cursor CURSOR FOR
        SELECT
            doc.nInsumoId,
            doc.nCantidad,
            doc.nPrecioUnitario,
            i.cNombreInsumo,
            i.nStockActual,
            ISNULL(i.nCostoPromedio, 0)
        FROM DetalleOrdenesCompra doc
        INNER JOIN Insumo i ON doc.nInsumoId = i.nInsumoId
        WHERE doc.nOrdenesCompraId = @OrdenCompraId AND i.bEstado = 1;

        OPEN detalle_compra_cursor;
        FETCH NEXT FROM detalle_compra_cursor INTO @InsumoId, @Cantidad, @PrecioUnitario,
                                                  @NombreInsumo, @StockActual, @CostoPromedioActual;

        WHILE @@FETCH_STATUS = 0
        BEGIN
            -- Calcular nuevo stock
            SET @NuevoStock = @StockActual + @Cantidad;

            -- Calcular nuevo costo promedio ponderado
            IF @StockActual > 0
                SET @NuevoCostoPromedio = ((@StockActual * @CostoPromedioActual) + (@Cantidad * @PrecioUnitario)) / @NuevoStock;
            ELSE
                SET @NuevoCostoPromedio = @PrecioUnitario;

            -- Actualizar stock y costo promedio en tabla Insumo
            UPDATE Insumo
            SET
                nStockActual = @NuevoStock,
                nCostoPromedio = @NuevoCostoPromedio
            WHERE nInsumoId = @InsumoId;

            -- Registrar movimiento en historial
            INSERT INTO MovimientoInventario (
                nTipoMovimientoInventarioId,
                nInsumoId,
                nCantidadMovimiento,
                nStockAnterior,
                nStockNuevo,
                cObservaciones,
                nPedidoID, -- NULL para compras
                cCodUsuario
            ) VALUES (
                @TipoMovimientoEntrada,
                @InsumoId,
                @Cantidad,
                @StockActual,
                @NuevoStock,
                'Entrada por compra - Proveedor: ' + @ProveedorNombre +
                ' - Precio: S/.' + CAST(@PrecioUnitario AS VARCHAR(20)) +
                ISNULL(' - ' + @Observaciones, ''),
                NULL,
                @Usuario
            );

            FETCH NEXT FROM detalle_compra_cursor INTO @InsumoId, @Cantidad, @PrecioUnitario,
                                                      @NombreInsumo, @StockActual, @CostoPromedioActual;
        END

        CLOSE detalle_compra_cursor;
        DEALLOCATE detalle_compra_cursor;

        -- Cambiar estado de la orden a "Recibida"
        UPDATE OrdenesCompra
        SET nEstadoOrdenCompraId = 2 -- 2 = Recibida
        WHERE nOrdenesCompraId = @OrdenCompraId;

        COMMIT TRANSACTION;

        SELECT 1 as Success, 'Orden de compra recibida e inventario actualizado correctamente' as Mensaje;

    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;

        -- Limpiar cursor en caso de error (usando nombre correcto)
        IF CURSOR_STATUS('local','detalle_compra_cursor') >= -1
        BEGIN
            CLOSE detalle_compra_cursor;
            DEALLOCATE detalle_compra_cursor;
        END

        SELECT 0 as Success, ERROR_MESSAGE() as Mensaje;
    END CATCH
END;