IF OBJECT_ID('PA_FormaPago_Ins_NuevoFormaPago') IS NOT NULL
    DROP PROCEDURE PA_FormaPago_Ins_NuevoFormaPago
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Inserta un nuevo FormaPago
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-09-03
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_FormaPago_Ins_NuevoFormaPago 'arkha', '9520668255'
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_FormaPago_Ins_NuevoFormaPago (
    @cNombreFormaPago				VARCHAR(100),
	@cImagen						VARCHAR(MAX)
)
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			INSERT INTO FormaPago(
				cNombreFormaPago, cImagen
			)
			VALUES (
				@cNombreFormaPago, @cImagen
			)


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
