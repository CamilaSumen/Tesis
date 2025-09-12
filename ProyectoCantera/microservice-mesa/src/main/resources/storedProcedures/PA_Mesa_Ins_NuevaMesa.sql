IF OBJECT_ID('PA_Mesa_Ins_NuevaMesa') IS NOT NULL
    DROP PROCEDURE PA_Mesa_Ins_NuevaMesa
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Inserta una nueva mesa.
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-09-03
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Mesa_Ins_NuevaMesa '01'
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Mesa_Ins_NuevaMesa (
    @cCodMesa				VARCHAR(3)
)
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			INSERT INTO Mesa (
				cCodMesa, cCodUsuario
			)
			VALUES (
				@cCodMesa, 'S/A'
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
