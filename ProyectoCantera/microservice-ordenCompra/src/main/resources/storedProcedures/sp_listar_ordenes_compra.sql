-- =============================================
-- SP: Listar órdenes de compra
-- =============================================
CREATE PROCEDURE sp_listar_ordenes_compra
(
    @FechaInicio DATE = NULL,
    @FechaFin DATE = NULL,
    @ProveedorId INT = NULL,
    @EstadoId INT = NULL
)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        oc.nOrdenesCompraId as OrdenCompraId,
        oc.dFecha as Fecha,
        p.nNombreProveedor as ProveedorNombre,
        p.nRuc as ProveedorRuc,
        eoc.cDescripcion as Estado,
        oc.nTotal as Total,
        COUNT(doc.nDetalleOrdenesCompraId) as TotalItems
    FROM OrdenesCompra oc
    INNER JOIN Proveedores p ON oc.nProveedorId = p.nProveedorId
    INNER JOIN EstadoOrdenCompra eoc ON oc.nEstadoOrdenCompraId = eoc.nEstadoOrdenCompraId
    LEFT JOIN DetalleOrdenesCompra doc ON oc.nOrdenesCompraId = doc.nOrdenesCompraId
    WHERE
        (@FechaInicio IS NULL OR CAST(oc.dFecha AS DATE) >= @FechaInicio) AND
        (@FechaFin IS NULL OR CAST(oc.dFecha AS DATE) <= @FechaFin) AND
        (@ProveedorId IS NULL OR oc.nProveedorId = @ProveedorId) AND
        (@EstadoId IS NULL OR oc.nEstadoOrdenCompraId = @EstadoId)
    GROUP BY oc.nOrdenesCompraId, oc.dFecha, p.nNombreProveedor, p.nRuc,
             eoc.cDescripcion, oc.nTotal
    ORDER BY oc.dFecha DESC;
END;
