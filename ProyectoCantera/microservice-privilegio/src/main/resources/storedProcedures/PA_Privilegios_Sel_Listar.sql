IF OBJECT_ID('PA_Privilegios_Sel_Listar') IS NOT NULL
    DROP PROCEDURE PA_Privilegios_Sel_Listar
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Lista todos los privilegios activos.
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Privilegios_Sel_Listar
-----------------------------------------------------------------------------------*/

CREATE PROCEDURE PA_Privilegios_Sel_Listar
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			SELECT
				nPrivilegiosId,
				cNombrePrivilegio,
				cDescripcion,
				cObservacion,
				bEstado
			FROM Privilegios WITH(NOLOCK)
	--WHERE bEstado = 1

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
