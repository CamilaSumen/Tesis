-- 2. SP para obtener resumen de estados de pedidos
CREATE PROCEDURE sp_obtener_estados_pedidos_resumen
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        SUM(CASE WHEN Estado = 'En Cocina' OR Estado = 'Preparando' THEN 1 ELSE 0 END) as EnCocina,
        SUM(CASE WHEN Estado = 'Pendiente' THEN 1 ELSE 0 END) as Pendientes,
        SUM(CASE WHEN Estado = 'Completado' AND CAST(FechaPedido AS DATE) = CAST(GETDATE() AS DATE) THEN 1 ELSE 0 END) as Completados,
        COUNT(*) as Total
    FROM Pedidos WITH(NOLOCK)
    WHERE CAST(FechaPedido AS DATE) = CAST(GETDATE() AS DATE);
END;