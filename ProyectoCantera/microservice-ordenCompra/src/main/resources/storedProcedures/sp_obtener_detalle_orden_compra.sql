-- =============================================
-- SP: Obtener detalle de orden de compra
-- =============================================
CREATE PROCEDURE sp_obtener_detalle_orden_compra
(
    @OrdenCompraId INT
)
AS
BEGIN
    SET NOCOUNT ON;

    -- Obtener datos de la orden con proveedor
    SELECT
        oc.nOrdenesCompraId as OrdenCompraId,
        oc.dFecha as Fecha,
        oc.nTotal as Total,
        p.nProveedorId as ProveedorId,
        p.nNombreProveedor as ProveedorNombre,
        p.nRuc as ProveedorRuc,
        p.nTelefono as ProveedorTelefono,
        p.nDireccion as ProveedorDireccion,
        eoc.nEstadoOrdenCompraId as EstadoId,
        eoc.cDescripcion as Estado
    FROM OrdenesCompra oc
    INNER JOIN Proveedores p ON oc.nProveedorId = p.nProveedorId
    INNER JOIN EstadoOrdenCompra eoc ON oc.nEstadoOrdenCompraId = eoc.nEstadoOrdenCompraId
    WHERE oc.nOrdenesCompraId = @OrdenCompraId;

    -- Obtener detalle de la orden
    SELECT
        doc.nDetalleOrdenesCompraId as DetalleId,
        doc.nInsumoId as InsumoId,
        i.cNombreInsumo as NombreInsumo,
        i.cUnidadMedida as UnidadMedida,
        ci.cNombre as Categoria,
        doc.nCantidad as Cantidad,
        doc.nPrecioUnitario as PrecioUnitario,
        (doc.nCantidad * doc.nPrecioUnitario) as Subtotal
    FROM DetalleOrdenesCompra doc
    INNER JOIN Insumo i ON doc.nInsumoId = i.nInsumoId
    INNER JOIN CategoriaInsumo ci ON i.nCategoriaInsumoId = ci.nCategoriaInsumoId
    WHERE doc.nOrdenesCompraId = @OrdenCompraId
    ORDER BY ci.cNombre, i.cNombreInsumo;
END;
