-- =============================================
-- SP: Cambiar estado de pedido en cocina
-- =============================================
CREATE PROCEDURE sp_cambiar_estado_pedido_cocina
    @PedidoID INT,
    @NuevoEstado VARCHAR(20)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @EstadoActual VARCHAR(20)

    -- Obtener estado actual
    SELECT @EstadoActual = Estado
    FROM Pedidos
    WHERE PedidoID = @PedidoID

    -- Validar que el pedido existe
    IF @EstadoActual IS NULL
    BEGIN
        SELECT 0 as Success, 'Pedido no encontrado' as Mensaje
        RETURN
    END

    -- Validar transiciones de estado válidas
    IF (@EstadoActual = 'Pendiente' AND @NuevoEstado NOT IN ('En Preparación', 'Cancelado'))
       OR (@EstadoActual = 'En Preparación' AND @NuevoEstado NOT IN ('Listo', 'Pendiente', 'Cancelado'))
       OR (@EstadoActual = 'Listo' AND @NuevoEstado NOT IN ('En Preparación', 'Entregado'))
    BEGIN
        SELECT 0 as Success, 'Transición de estado no válida' as Mensaje
        RETURN
    END

    -- Actualizar estado
    UPDATE Pedidos
    SET Estado = @NuevoEstado,
        FechaActualizacion = GETDATE()
    WHERE PedidoID = @PedidoID

    SELECT 1 as Success,
           CASE
               WHEN @NuevoEstado = 'En Preparación' THEN 'Pedido iniciado en preparación'
               WHEN @NuevoEstado = 'Listo' THEN 'Pedido marcado como listo'
               WHEN @NuevoEstado = 'Pendiente' THEN 'Pedido regresado a pendiente'
               ELSE 'Estado actualizado'
           END as Mensaje
END