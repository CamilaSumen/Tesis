-- =============================================
-- SP: Obtener estadísticas de cocina
-- =============================================
CREATE PROCEDURE sp_obtener_estadisticas_cocina
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @Pendientes INT = 0
    DECLARE @EnPreparacion INT = 0
    DECLARE @Listos INT = 0
    DECLARE @TotalHoy INT = 0
    DECLARE @PromedioTiempo INT = 0

    -- Contar por estados
    SELECT @Pendientes = COUNT(*)
    FROM Pedidos
    WHERE Estado = 'Pendiente'

    SELECT @EnPreparacion = COUNT(*)
    FROM Pedidos
    WHERE Estado = 'En Preparación'

    SELECT @Listos = COUNT(*)
    FROM Pedidos
    WHERE Estado = 'Listo'

    -- Total de pedidos de hoy
    SELECT @TotalHoy = COUNT(*)
    FROM Pedidos
    WHERE CAST(FechaPedido AS DATE) = CAST(GETDATE() AS DATE)

    -- Promedio de tiempo de preparación (pedidos completados hoy)
    SELECT @PromedioTiempo = AVG(DATEDIFF(MINUTE, FechaPedido, FechaActualizacion))
    FROM Pedidos
    WHERE Estado IN ('Listo', 'Entregado')
        AND CAST(FechaPedido AS DATE) = CAST(GETDATE() AS DATE)
        AND FechaActualizacion IS NOT NULL

    SELECT
        @Pendientes as Pendientes,
        @EnPreparacion as EnPreparacion,
        @Listos as Listos,
        @TotalHoy as TotalHoy,
        ISNULL(@PromedioTiempo, 0) as PromedioTiempoMinutos
END