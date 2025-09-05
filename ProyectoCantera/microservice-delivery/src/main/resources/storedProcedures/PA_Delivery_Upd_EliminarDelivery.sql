IF OBJECT_ID('PA_Delivery_Upd_EliminarDelivery') IS NOT NULL
    DROP PROCEDURE PA_Delivery_Upd_EliminarDelivery
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Eliminación lógica de la Delivery (nEstado = 0).
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Delivery_Upd_EliminarDelivery 1
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Delivery_Upd_EliminarDelivery (
    @nDeliveryId		INT
)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRAN

        UPDATE Delivery
        SET bEstado = 0
        WHERE nDeliveryId = @nDeliveryId;

		COMMIT TRAN
	END TRY
	BEGIN CATCH
		IF @@TRANCOUNT > 0
			ROLLBACK TRAN
		DECLARE @ErrorMessage NVARCHAR(4000)
		DECLARE @ErrorSeverity INT
		DECLARE @ErrorState INT

		SELECT
			@ErrorMessage = ERROR_MESSAGE(),
			@ErrorSeverity = ERROR_SEVERITY(),
			@ErrorState = ERROR_STATE()
		RAISERROR(@ErrorMessage, @ErrorSeverity, @ErrorState)
	END CATCH
END
