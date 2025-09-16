-- =============================================
-- SP: Obtener resumen de caja actual
-- =============================================
CREATE PROCEDURE sp_obtener_resumen_caja_actual
    @Usuario VARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @SesionActiva INT;

    -- Obtener sesión activa
    SELECT @SesionActiva = nSesionCajaId
    FROM SesionCaja
    WHERE cUsuario = @Usuario AND cEstado = 'Abierta'
    ORDER BY dFechaApertura DESC;

    IF @SesionActiva IS NULL
    BEGIN
        SELECT
            0 as TieneSesionActiva,
            'No hay sesión activa' as Mensaje;
        RETURN;
    END

    -- Obtener resumen de la sesión activa
    SELECT
        1 as TieneSesionActiva,
        sc.nSesionCajaId,
        sc.cUsuario,
        sc.dFechaApertura,
        sc.nMontoApertura,
        ISNULL(SUM(CASE WHEN tmc.bEsIngreso = 1 THEN mc.nMonto ELSE 0 END), 0) as TotalIngresos,
        ISNULL(SUM(CASE WHEN tmc.bEsIngreso = 0 THEN mc.nMonto ELSE 0 END), 0) as TotalEgresos,
        sc.nMontoApertura +
        ISNULL(SUM(CASE WHEN tmc.bEsIngreso = 1 THEN mc.nMonto ELSE 0 END), 0) -
        ISNULL(SUM(CASE WHEN tmc.bEsIngreso = 0 THEN mc.nMonto ELSE 0 END), 0) as MontoActualCaja,
        COUNT(CASE WHEN mc.nPedidoID IS NOT NULL THEN 1 END) as TotalVentas
    FROM SesionCaja sc
    LEFT JOIN MovimientoCaja mc ON sc.nSesionCajaId = mc.nSesionCajaId
    LEFT JOIN TipoMovimientoCaja tmc ON mc.nTipoMovimientoCajaId = tmc.nTipoMovimientoCajaId
    WHERE sc.nSesionCajaId = @SesionActiva
    GROUP BY sc.nSesionCajaId, sc.cUsuario, sc.dFechaApertura, sc.nMontoApertura
END