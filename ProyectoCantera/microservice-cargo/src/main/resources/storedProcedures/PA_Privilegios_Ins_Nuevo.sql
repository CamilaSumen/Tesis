IF OBJECT_ID('PA_Privilegios_Ins_Nuevo') IS NOT NULL
    DROP PROCEDURE PA_Privilegios_Ins_Nuevo
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Inserta un nuevo privilegio.
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Privilegios_Ins_Nuevo 'ADMIN', 'TIENE ACCESO A TODO', 'Privilegio PARA PERSONAL AUTORIZADO'
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Privilegios_Ins_Nuevo(
	@cNombrePrivilegio	VARCHAR(50),
	@cDescripcion VARCHAR(200),
	@cObservacion VARCHAR(200)
)
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

		INSERT INTO Privilegios (cNombrePrivilegio, cDescripcion, cObservacion, bEstado)
		VALUES (@cNombrePrivilegio, @cDescripcion, @cObservacion, 1)

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
