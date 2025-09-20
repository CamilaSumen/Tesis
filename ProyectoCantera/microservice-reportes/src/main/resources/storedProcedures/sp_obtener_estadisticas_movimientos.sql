-- 4. SP para obtener estadísticas de movimientos
CREATE PROCEDURE sp_obtener_estadisticas_movimientos
(
    @FechaDesde DATE = NULL,
    @FechaHasta DATE = NULL
)
AS
BEGIN
    SET NOCOUNT ON;

    -- Si no se especifican fechas, usar el día actual
    IF @FechaDesde IS NULL SET @FechaDesde = CAST(GETDATE() AS DATE);
    IF @FechaHasta IS NULL SET @FechaHasta = CAST(GETDATE() AS DATE);

    -- Estadísticas de inventario
    DECLARE @TotalMovimientosInventario INT;
    DECLARE @TotalEntradasInventario INT;
    DECLARE @TotalSalidasInventario INT;

    SELECT
        @TotalMovimientosInventario = COUNT(*),
        @TotalEntradasInventario = SUM(CASE WHEN nCantidadMovimiento > 0 THEN 1 ELSE 0 END),
        @TotalSalidasInventario = SUM(CASE WHEN nCantidadMovimiento < 0 THEN 1 ELSE 0 END)
    FROM MovimientoInventario
    WHERE CAST(dFechaMovimiento AS DATE) BETWEEN @FechaDesde AND @FechaHasta;

    -- Estadísticas de caja
    DECLARE @TotalMovimientosCaja INT;
    DECLARE @TotalIngresosCaja DECIMAL(10,2);
    DECLARE @TotalEgresosCaja DECIMAL(10,2);
    DECLARE @TotalVentas INT;

    SELECT
        @TotalMovimientosCaja = COUNT(*),
        @TotalIngresosCaja = ISNULL(SUM(CASE WHEN tmc.bEsIngreso = 1 THEN mc.nMonto ELSE 0 END), 0),
        @TotalEgresosCaja = ISNULL(SUM(CASE WHEN tmc.bEsIngreso = 0 THEN mc.nMonto ELSE 0 END), 0),
        @TotalVentas = SUM(CASE WHEN mc.nPedidoID IS NOT NULL THEN 1 ELSE 0 END)
    FROM MovimientoCaja mc
    INNER JOIN TipoMovimientoCaja tmc ON mc.nTipoMovimientoCajaId = tmc.nTipoMovimientoCajaId
    WHERE CAST(mc.dFechaMovimiento AS DATE) BETWEEN @FechaDesde AND @FechaHasta;

    -- Retornar estadísticas consolidadas
    SELECT
        -- Inventario
        ISNULL(@TotalMovimientosInventario, 0) as TotalMovimientosInventario,
        ISNULL(@TotalEntradasInventario, 0) as TotalEntradasInventario,
        ISNULL(@TotalSalidasInventario, 0) as TotalSalidasInventario,

        -- Caja
        ISNULL(@TotalMovimientosCaja, 0) as TotalMovimientosCaja,
        ISNULL(@TotalIngresosCaja, 0) as TotalIngresosCaja,
        ISNULL(@TotalEgresosCaja, 0) as TotalEgresosCaja,
        ISNULL(@TotalVentas, 0) as TotalVentas,

        -- Balance
        ISNULL(@TotalIngresosCaja, 0) - ISNULL(@TotalEgresosCaja, 0) as BalanceNeto;
END;
