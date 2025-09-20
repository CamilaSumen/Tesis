-- 5. SP para obtener resumen de delivery
CREATE PROCEDURE sp_obtener_resumen_delivery
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        SUM(CASE
            WHEN (Observaciones LIKE '%delivery%' OR Observaciones LIKE '%envío%')
                 AND Estado IN ('En Camino', 'Despachado')
            THEN 1 ELSE 0
        END) as EnCamino,
        SUM(CASE
            WHEN (Observaciones LIKE '%delivery%' OR Observaciones LIKE '%envío%')
                 AND Estado = 'Pendiente'
            THEN 1 ELSE 0
        END) as SinAsignar,
        SUM(CASE
            WHEN (Observaciones LIKE '%delivery%' OR Observaciones LIKE '%envío%')
                 AND Estado = 'Completado'
                 AND CAST(FechaPedido AS DATE) = CAST(GETDATE() AS DATE)
            THEN 1 ELSE 0
        END) as Completados
    FROM Pedidos WITH(NOLOCK)
    WHERE CAST(FechaPedido AS DATE) = CAST(GETDATE() AS DATE);
END;