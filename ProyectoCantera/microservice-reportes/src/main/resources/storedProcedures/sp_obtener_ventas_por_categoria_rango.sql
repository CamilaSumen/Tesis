-- SP para obtener ventas por categoría en un rango
CREATE PROCEDURE sp_obtener_ventas_por_categoria_rango
(
    @FechaInicio DATE,
    @FechaFin DATE
)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        cp.cNombreCategoria as Categoria,
        SUM(dp.Subtotal) as Total
    FROM DetallePedido dp WITH(NOLOCK)
    INNER JOIN Pedidos p WITH(NOLOCK) ON dp.PedidoID = p.PedidoID
    INNER JOIN Comprobantes c WITH(NOLOCK) ON p.PedidoID = c.PedidoID
    INNER JOIN Producto pr WITH(NOLOCK) ON dp.ProductoID = pr.nProductoId
    INNER JOIN CategoriaProducto cp WITH(NOLOCK) ON pr.nCategoriaProductoId = cp.nCategoriaProductoId
    WHERE CAST(p.FechaPedido AS DATE) BETWEEN @FechaInicio AND @FechaFin
        AND c.Estado = 'PAGADO'
    GROUP BY cp.cNombreCategoria
    ORDER BY SUM(dp.Subtotal) DESC;
END;