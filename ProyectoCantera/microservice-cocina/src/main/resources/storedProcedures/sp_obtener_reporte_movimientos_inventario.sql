-- =============================================
-- SP: Obtener reporte de movimientos de inventario
-- =============================================
CREATE PROCEDURE sp_obtener_reporte_movimientos_inventario
    @FechaDesde DATE = NULL,
    @FechaHasta DATE = NULL,
    @InsumoId INT = NULL,
    @TipoMovimientoId INT = NULL
AS
BEGIN
    SET NOCOUNT ON;

    -- Si no se especifican fechas, usar el mes actual
    IF @FechaDesde IS NULL SET @FechaDesde = DATEFROMPARTS(YEAR(GETDATE()), MONTH(GETDATE()), 1);
    IF @FechaHasta IS NULL SET @FechaHasta = EOMONTH(GETDATE());

    SELECT
        mi.nMovimientoInventarioId,
        mi.dFechaMovimiento,
        tmi.cDescripcion as TipoMovimiento,
        i.cNombreInsumo,
        i.cUnidadMedida,
        mi.nCantidadMovimiento,
        mi.nStockAnterior,
        mi.nStockNuevo,
        mi.cObservaciones,
        mi.nPedidoID,
        CASE
            WHEN mi.nCantidadMovimiento > 0 THEN 'ENTRADA'
            ELSE 'SALIDA'
        END as TipoOperacion
    FROM MovimientoInventario mi
    INNER JOIN TipoMovimientoInventario tmi ON mi.nTipoMovimientoInventarioId = tmi.nTipoMovimientoInventarioId
    INNER JOIN Insumo i ON mi.nInsumoId = i.nInsumoId
    WHERE CAST(mi.dFechaMovimiento AS DATE) BETWEEN @FechaDesde AND @FechaHasta
        AND (@InsumoId IS NULL OR mi.nInsumoId = @InsumoId)
        AND (@TipoMovimientoId IS NULL OR mi.nTipoMovimientoInventarioId = @TipoMovimientoId)
    ORDER BY mi.dFechaMovimiento DESC, mi.nMovimientoInventarioId DESC
END