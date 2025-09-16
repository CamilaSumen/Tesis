-- =============================================
-- SP: Obtener detalle completo de pedido para cocina (EN UNA SOLA CONSULTA)
-- =============================================
CREATE PROCEDURE sp_obtener_detalle_pedido_cocina
    @PedidoID INT
AS
BEGIN
    SET NOCOUNT ON;

    -- Todo en una sola consulta con JOIN
    SELECT
        p.PedidoID,
        p.Mesa,
        p.Mozo,
        p.NumeroPersonas,
        p.FechaPedido,
        p.Estado,
        p.Observaciones,
        p.Total,
        dp.DetalleID,
        dp.NombreProducto,
        dp.Cantidad,
        dp.PrecioUnitario,
        dp.Subtotal
    FROM Pedidos p
    INNER JOIN DetallePedido dp ON p.PedidoID = dp.PedidoID
    WHERE p.PedidoID = @PedidoID
    ORDER BY dp.DetalleID
END