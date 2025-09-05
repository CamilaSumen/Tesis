IF OBJECT_ID('PA_Mesa_Sel_ListarDelivery') IS NOT NULL
    DROP PROCEDURE PA_Mesa_Sel_ListarDelivery
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Lista todas los deliverys
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-09-03
EJEMPLO:
EXEC PA_Mesa_Sel_ListarDelivery
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Mesa_Sel_ListarDelivery
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			SELECT
				T1.nDeliveryId,
				T1.cNombre,
				T1.cTelefono,
				T1.bEstado
			FROM Delivery T1 WITH(NOLOCK)

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
