exec sp_obtener_pedido_para_editar 3

CREATE PROCEDURE sp_obtener_pedido_para_editar
(
    @PedidoID INT
)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        p.PedidoID,
        p.Mesa,
        p.Mozo,
        p.NumeroPersonas,
        p.FechaPedido,
        p.Estado,
        p.Observaciones,
        p.Total,

        -- Datos del detalle del pedido
        d.DetalleID,
        d.ProductoID as productoID,
        d.NombreProducto as nombreProducto,
        d.Cantidad as cantidad,
        d.PrecioUnitario as precioUnitario,
        d.Subtotal as subtotal

    FROM Pedidos p
    INNER JOIN DetallePedido d ON p.PedidoID = d.PedidoID
    WHERE p.PedidoID = @PedidoID
    ORDER BY d.DetalleID;
END;