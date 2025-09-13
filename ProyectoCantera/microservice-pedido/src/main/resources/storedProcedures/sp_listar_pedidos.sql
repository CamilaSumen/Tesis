CREATE PROCEDURE sp_listar_pedidos
AS
BEGIN
    SELECT
        PedidoID,
        Mesa,
        Mozo,
        NumeroPersonas,
        FechaPedido,
        Estado,
        Observaciones,
        Total,
        FechaCreacion,
        FechaActualizacion
    FROM Pedidos
    ORDER BY FechaPedido DESC;
END;