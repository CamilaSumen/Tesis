-- =============================================
-- SP: Anular orden de compra
-- =============================================
CREATE PROCEDURE sp_anular_orden_compra
(
    @OrdenCompraId INT,
    @Usuario VARCHAR(100),
    @MotivoAnulacion VARCHAR(500)
)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @EstadoActual INT;

    -- Verificar que la orden existe y está pendiente
    SELECT @EstadoActual = nEstadoOrdenCompraId
    FROM OrdenesCompra
    WHERE nOrdenesCompraId = @OrdenCompraId;

    IF @EstadoActual IS NULL
    BEGIN
        SELECT 0 as Success, 'Orden de compra no encontrada' as Mensaje;
        RETURN;
    END

    IF @EstadoActual != 1 -- 1 = Pendiente
    BEGIN
        SELECT 0 as Success, 'Solo se pueden anular órdenes en estado Pendiente' as Mensaje;
        RETURN;
    END

    BEGIN TRY
        -- Cambiar estado a anulada
        UPDATE OrdenesCompra
        SET nEstadoOrdenCompraId = 3 -- 3 = Anulada
        WHERE nOrdenesCompraId = @OrdenCompraId;

        SELECT 1 as Success, 'Orden de compra anulada correctamente' as Mensaje;

    END TRY
    BEGIN CATCH
        SELECT 0 as Success, ERROR_MESSAGE() as Mensaje;
    END CATCH
END;