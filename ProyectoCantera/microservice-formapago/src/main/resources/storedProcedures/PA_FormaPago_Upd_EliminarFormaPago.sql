IF OBJECT_ID('PA_FormaPago_Upd_EliminarFormaPago') IS NOT NULL
    DROP PROCEDURE PA_FormaPago_Upd_EliminarFormaPago
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Eliminación lógica de la FormaPago (nEstado = 0).
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_FormaPago_Upd_EliminarFormaPago 1
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_FormaPago_Upd_EliminarFormaPago (
    @nFormaPagoId		INT
)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRAN

        UPDATE FormaPago
        SET bEstado = 0
        WHERE nFormaPagoId = @nFormaPagoId;

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
