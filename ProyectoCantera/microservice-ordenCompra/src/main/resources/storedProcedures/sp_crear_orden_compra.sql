-- =============================================
-- SP: Crear orden de compra
-- =============================================
CREATE PROCEDURE sp_crear_orden_compra
(
    @ProveedorId INT,
    @Usuario VARCHAR(100),
    @Observaciones VARCHAR(500) = NULL,
    @DetalleJson NVARCHAR(MAX) -- JSON con array de insumos: [{"insumoId":1,"cantidad":10,"precioUnitario":5.50}]
)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @OrdenCompraId INT;
    DECLARE @Total DECIMAL(10,2) = 0;

    BEGIN TRY
        BEGIN TRANSACTION;

        -- Validar que el proveedor existe
        IF NOT EXISTS (SELECT 1 FROM Proveedores WHERE nProveedorId = @ProveedorId AND bEstado = 1)
        BEGIN
            RAISERROR('Proveedor no encontrado o inactivo', 16, 1);
            RETURN;
        END

        -- Crear la orden de compra
        INSERT INTO OrdenesCompra (
            nProveedorId,
            nEstadoOrdenCompraId,
            dFecha,
            nTotal
        ) VALUES (
            @ProveedorId,
            1, -- Estado: Pendiente
            GETDATE(),
            0 -- Se calculará después
        );

        SET @OrdenCompraId = SCOPE_IDENTITY();

        -- Procesar el detalle desde JSON
        INSERT INTO DetalleOrdenesCompra (
            nOrdenesCompraId,
            nInsumoId,
            dFecha,
            nCantidad,
            nPrecioUnitario
        )
        SELECT
            @OrdenCompraId,
            JSON_VALUE(value, '$.insumoId'),
            GETDATE(),
            CAST(JSON_VALUE(value, '$.cantidad') AS DECIMAL(10,2)),
            CAST(JSON_VALUE(value, '$.precioUnitario') AS DECIMAL(10,2))
        FROM OPENJSON(@DetalleJson);

        -- Calcular el total
        SELECT @Total = SUM(nCantidad * nPrecioUnitario)
        FROM DetalleOrdenesCompra
        WHERE nOrdenesCompraId = @OrdenCompraId;

        -- Actualizar el total en la orden
        UPDATE OrdenesCompra
        SET nTotal = @Total
        WHERE nOrdenesCompraId = @OrdenCompraId;

        COMMIT TRANSACTION;

        -- Retornar información de la orden creada
        SELECT
            @OrdenCompraId as OrdenCompraId,
            @Total as Total,
            'Orden de compra creada exitosamente' as Mensaje,
            1 as Success
        FROM OrdenesCompra
        WHERE nOrdenesCompraId = @OrdenCompraId;

    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;

        SELECT
            0 as Success,
            ERROR_MESSAGE() as Mensaje;
    END CATCH
END;