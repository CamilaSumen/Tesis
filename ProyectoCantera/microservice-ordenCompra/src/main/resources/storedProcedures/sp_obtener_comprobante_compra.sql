ALTER PROCEDURE sp_obtener_comprobante_compra
(
    @OrdenCompraId INT
)
AS
BEGIN
    SET NOCOUNT ON;

    -- Obtener datos del comprobante con proveedor
    SELECT
        cc.nComprobanteCompraId as ComprobanteCompraId,
        cc.nOrdenCompraId as OrdenCompraId,
        cc.nProveedorId as ProveedorId,
        cc.cTipoComprobante as TipoComprobante,
        cc.cNumeroComprobante as NumeroComprobante,
        cc.dFechaPago as FechaPago,
        cc.cTipoPago as TipoPago,
        cc.nSubtotal as Subtotal,
        cc.nIGV as IGV,
        cc.nTotalFinal as TotalFinal,
        cc.cEstado as Estado,
        cc.cObservaciones as Observaciones,

        -- Datos del proveedor
        p.nNombreProveedor as ProveedorNombre,
        p.nRuc as ProveedorRuc,
        p.nDireccion as ProveedorDireccion,
        p.nTelefono as ProveedorTelefono

    FROM ComprobantesCompra cc
    INNER JOIN Proveedores p ON cc.nProveedorId = p.nProveedorId
    WHERE cc.nOrdenCompraId = @OrdenCompraId;

    -- Obtener detalle del comprobante
    SELECT
        dcc.nDetalleComprobanteCompraId as DetalleId,
        dcc.nComprobanteCompraId as ComprobanteCompraId,
        dcc.nInsumoId as InsumoId,
        dcc.cNombreInsumo as NombreInsumo,
        dcc.nCantidad as Cantidad,
        dcc.nPrecioUnitario as PrecioUnitario,
        dcc.nSubtotal as Subtotal
    FROM DetalleComprobanteCompra dcc
    INNER JOIN ComprobantesCompra cc ON dcc.nComprobanteCompraId = cc.nComprobanteCompraId
    WHERE cc.nOrdenCompraId = @OrdenCompraId
    ORDER BY dcc.nDetalleComprobanteCompraId;
END