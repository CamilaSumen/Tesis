CREATE PROCEDURE sp_generar_comprobante_compra
(
    @OrdenCompraId INT,
    @TipoComprobante VARCHAR(20) = 'FACTURA',
    @TipoPago VARCHAR(50),
    @Usuario VARCHAR(100),
    @Observaciones VARCHAR(500) = NULL
)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @ComprobanteCompraId INT;
    DECLARE @ProveedorId INT;
    DECLARE @Total DECIMAL(10,2);
    DECLARE @NumeroComprobante VARCHAR(50);
    DECLARE @Subtotal DECIMAL(10,2);
    DECLARE @IGV DECIMAL(10,2);

    BEGIN TRY
        BEGIN TRANSACTION;

        -- Obtener datos de la orden
        SELECT @ProveedorId = nProveedorId, @Total = nTotal
        FROM OrdenesCompra WITH(NOLOCK)
        WHERE nOrdenesCompraId = @OrdenCompraId;

        IF @ProveedorId IS NULL
        BEGIN
            RAISERROR('Orden de compra no encontrada', 16, 1);
            RETURN;
        END

        -- Calcular subtotal e IGV (18%)
        SET @Subtotal = @Total / 1.18;
        SET @IGV = @Total - @Subtotal;

        -- Generar número de comprobante
        DECLARE @Correlativo INT;
        SELECT @Correlativo = ISNULL(MAX(nComprobanteCompraId), 0) + 1 FROM ComprobantesCompra WITH(NOLOCK);

        IF @TipoComprobante = 'FACTURA'
            SET @NumeroComprobante = 'FC001-' + FORMAT(@Correlativo, '00000000');
        ELSE IF @TipoComprobante = 'BOLETA'
            SET @NumeroComprobante = 'BC001-' + FORMAT(@Correlativo, '00000000');
        ELSE
            SET @NumeroComprobante = 'RC001-' + FORMAT(@Correlativo, '00000000');

        -- Insertar comprobante
        INSERT INTO ComprobantesCompra (
            nOrdenCompraId, nProveedorId, cTipoComprobante, cNumeroComprobante,
            cTipoPago, nSubtotal, nIGV, nTotalFinal, cObservaciones
        )
        VALUES (
            @OrdenCompraId, @ProveedorId, @TipoComprobante, @NumeroComprobante,
            @TipoPago, @Subtotal, @IGV, @Total, @Observaciones
        );

        SET @ComprobanteCompraId = SCOPE_IDENTITY();

        -- Copiar detalle de la orden al comprobante
        INSERT INTO DetalleComprobanteCompra (
            nComprobanteCompraId, nInsumoId, cNombreInsumo, nCantidad, nPrecioUnitario, nSubtotal
        )
        SELECT
            @ComprobanteCompraId,
            i.nInsumoId,
            i.cNombreInsumo,
            doc.nCantidad,
            doc.nPrecioUnitario,
            (doc.nCantidad * doc.nPrecioUnitario)
        FROM DetalleOrdenesCompra doc WITH(NOLOCK)
        INNER JOIN Insumo i WITH(NOLOCK) ON doc.nInsumoId = i.nInsumoId
        WHERE doc.nOrdenesCompraId = @OrdenCompraId;

        COMMIT TRANSACTION

        -- Retornar información del comprobante
        SELECT
            @ComprobanteCompraId as ComprobanteCompraId,
            @NumeroComprobante as NumeroComprobante,
            @Total as TotalFinal,
            'Comprobante de compra generado exitosamente' as Mensaje,
            1 as Success;

    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;

        SELECT
            0 as Success,
            ERROR_MESSAGE() as Mensaje;
    END CATCH
END;