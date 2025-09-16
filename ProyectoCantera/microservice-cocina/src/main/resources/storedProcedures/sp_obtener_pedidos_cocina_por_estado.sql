-- =============================================
-- SP: Obtener pedidos por estado para cocina
-- =============================================
CREATE PROCEDURE sp_obtener_pedidos_cocina_por_estado
    @Estado VARCHAR(20)
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
        COUNT(dp.DetalleID) as TotalItems
    FROM Pedidos p
    LEFT JOIN DetallePedido dp ON p.PedidoID = dp.PedidoID
    WHERE p.Estado = @Estado
        AND p.Estado IN ('Pendiente', 'En Preparación', 'Listo')
    GROUP BY p.PedidoID, p.Mesa, p.Mozo, p.NumeroPersonas,
             p.FechaPedido, p.Estado, p.Observaciones, p.Total
    ORDER BY p.FechaPedido ASC
END