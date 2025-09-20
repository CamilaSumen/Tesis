-- SP para obtener producto más vendido en un rango
CREATE PROCEDURE sp_obtener_producto_mas_vendido_rango
(
    @FechaInicio DATE,
    @FechaFin DATE
)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT TOP 1
        dp.ProductoID,
        dp.NombreProducto,
        SUM(dp.Cantidad) as CantidadVendida,
        SUM(dp.Subtotal) as TotalIngresos
    FROM DetallePedido dp WITH(NOLOCK)
    INNER JOIN Pedidos p WITH(NOLOCK) ON dp.PedidoID = p.PedidoID
    INNER JOIN Comprobantes c WITH(NOLOCK) ON p.PedidoID = c.PedidoID
    WHERE CAST(p.FechaPedido AS DATE) BETWEEN @FechaInicio AND @FechaFin
        AND c.Estado = 'PAGADO'
    GROUP BY dp.ProductoID, dp.NombreProducto
    ORDER BY SUM(dp.Cantidad) DESC;
END;