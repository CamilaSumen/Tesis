IF OBJECT_ID('Cargo_Upd_ModificarCargo') IS NOT NULL
    DROP PROCEDURE Cargo_Upd_ModificarCargo
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Actualiza el Cargo seleccionado.
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-04-22
-----------------------------------------------------------------------------------

EJEMPLO:
	EXEC Cargo_Upd_ModificarCargo 1, 'MESERO', 'HOLAS'
--------------------------------------------------- --------------------------------*/

CREATE PROCEDURE Cargo_Upd_ModificarCargo(
	@nCargoId		INT,
	@cNombreCargo	VARCHAR(100),
	@cDescripcion	VARCHAR(200)
)
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			UPDATE Cargo
			SET
				cNombreCargo = @cNombreCargo,
				cDescripcion = @cDescripcion
			WHERE nCargoId = @nCargoId


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