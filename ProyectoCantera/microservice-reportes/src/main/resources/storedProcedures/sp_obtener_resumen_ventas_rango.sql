-- SP para obtener resumen de ventas en un rango de fechas
CREATE PROCEDURE sp_obtener_resumen_ventas_rango
(
    @FechaInicio DATE,
    @FechaFin DATE
)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        ISNULL(SUM(c.TotalFinal), 0) as TotalVentas,
        COUNT(DISTINCT c.PedidoID) as CantidadPedidos,
        CASE
            WHEN COUNT(DISTINCT c.PedidoID) > 0
            THEN ISNULL(SUM(c.TotalFinal), 0) / COUNT(DISTINCT c.PedidoID)
            ELSE 0
        END as PromedioTicket
    FROM Comprobantes c WITH(NOLOCK)
    WHERE CAST(c.FechaPago AS DATE) BETWEEN @FechaInicio AND @FechaFin
        AND c.Estado = 'PAGADO';
END;