-- =============================================
-- SP: Obtener reporte de caja por sesión
-- =============================================
CREATE PROCEDURE sp_obtener_reporte_caja
    @SesionId INT = NULL,
    @Usuario VARCHAR(100) = NULL,
    @FechaDesde DATE = NULL,
    @FechaHasta DATE = NULL
AS
BEGIN
    SET NOCOUNT ON;

    -- Si no se especifican fechas, usar el día actual
    IF @FechaDesde IS NULL SET @FechaDesde = CAST(GETDATE() AS DATE);
    IF @FechaHasta IS NULL SET @FechaHasta = CAST(GETDATE() AS DATE);

    SELECT
        mc.nMovimientoCajaId,
        sc.nSesionCajaId,
        sc.cUsuario,
        mc.dFechaMovimiento,
        tmc.cDescripcion as TipoMovimiento,
        tmc.bEsIngreso,
        mc.cDescripcion,
        mc.nMonto,
        mc.cTipoPago,
        mc.nPedidoID,
        mc.nComprobanteID,
        mc.cObservaciones,
        CASE
            WHEN tmc.bEsIngreso = 1 THEN 'INGRESO'
            ELSE 'EGRESO'
        END as TipoOperacion
    FROM MovimientoCaja mc
    INNER JOIN SesionCaja sc ON mc.nSesionCajaId = sc.nSesionCajaId
    INNER JOIN TipoMovimientoCaja tmc ON mc.nTipoMovimientoCajaId = tmc.nTipoMovimientoCajaId
    WHERE (@SesionId IS NULL OR sc.nSesionCajaId = @SesionId)
        AND (@Usuario IS NULL OR sc.cUsuario = @Usuario)
        AND CAST(mc.dFechaMovimiento AS DATE) BETWEEN @FechaDesde AND @FechaHasta
    ORDER BY mc.dFechaMovimiento DESC, mc.nMovimientoCajaId DESC
END