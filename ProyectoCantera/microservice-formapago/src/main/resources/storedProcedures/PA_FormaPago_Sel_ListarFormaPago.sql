IF OBJECT_ID('PA_FormaPago_Sel_ListarFormaPago') IS NOT NULL
    DROP PROCEDURE PA_FormaPago_Sel_ListarFormaPago
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Lista todas los FormaPagos
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-09-03
EJEMPLO:
EXEC PA_FormaPago_Sel_ListarFormaPago
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_FormaPago_Sel_ListarFormaPago
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			SELECT
				T1.nFormaPagoId,
				T1.cNombreFormaPago,
				T1.cImagen,
				T1.bEstado
			FROM FormaPago T1 WITH(NOLOCK)

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
