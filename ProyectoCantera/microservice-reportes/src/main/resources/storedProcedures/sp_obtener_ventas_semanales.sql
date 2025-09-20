-- 6. SP para obtener ventas de los últimos 7 días
CREATE PROCEDURE sp_obtener_ventas_semanales
(
    @FechaDesde DATE,
    @FechaHasta DATE
)
AS
BEGIN
    SET NOCOUNT ON;

    WITH FechasRango AS (
        SELECT @FechaDesde as Fecha
        UNION ALL
        SELECT DATEADD(DAY, 1, Fecha)
        FROM FechasRango
        WHERE Fecha < @FechaHasta
    )
    SELECT
        fr.Fecha,
        ISNULL(SUM(c.TotalFinal), 0) as Total,
        COUNT(DISTINCT c.PedidoID) as CantidadPedidos
    FROM FechasRango fr
    LEFT JOIN Comprobantes c WITH(NOLOCK)
        ON CAST(c.FechaPago AS DATE) = fr.Fecha
        AND c.Estado = 'PAGADO'
    GROUP BY fr.Fecha
    ORDER BY fr.Fecha;
END;