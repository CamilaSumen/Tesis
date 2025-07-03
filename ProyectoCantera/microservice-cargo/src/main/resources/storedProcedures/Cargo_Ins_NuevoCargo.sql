IF OBJECT_ID('Cargo_Ins_NuevoCargo') IS NOT NULL
    DROP PROCEDURE Cargo_Ins_NuevoCargo
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Inserta Nuevos Cargos.
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-04-22
-----------------------------------------------------------------------------------

EJEMPLO:
	EXEC Cargo_Ins_NuevoCargo 'COCINERO', 'ENCARGADO DE LA COCINA'
-----------------------------------------------------------------------------------*/

CREATE PROCEDURE Cargo_Ins_NuevoCargo(
	@cNombreCargo	VARCHAR(100),
	@cDescripcion	VARCHAR(200)
)
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			INSERT INTO Cargo (cNombreCargo, cDescripcion, bEstado)
			VALUES(@cNombreCargo, @cDescripcion, 1)


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