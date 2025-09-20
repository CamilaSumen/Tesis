-- 8. SP para obtener desglose de caja por tipo de pago
CREATE PROCEDURE sp_obtener_desglose_caja_dia
(
    @Fecha DATE
)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        mc.cTipoPago as TipoPago,
        SUM(mc.nMonto) as Monto
    FROM MovimientoCaja mc WITH(NOLOCK)
    INNER JOIN TipoMovimientoCaja tmc WITH(NOLOCK) ON mc.nTipoMovimientoCajaId = tmc.nTipoMovimientoCajaId
    WHERE CAST(mc.dFechaMovimiento AS DATE) = @Fecha
        AND tmc.bEsIngreso = 1
        AND mc.cTipoPago IS NOT NULL
    GROUP BY mc.cTipoPago
    ORDER BY SUM(mc.nMonto) DESC;
END;