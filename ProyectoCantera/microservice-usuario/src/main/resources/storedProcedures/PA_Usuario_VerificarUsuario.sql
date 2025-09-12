IF OBJECT_ID('PA_Usuario_VerificarUsuario') IS NOT NULL
    DROP PROCEDURE PA_Usuario_VerificarUsuario
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Lista todos los usuarios activos.
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-07-05
EJEMPLO:
EXEC PA_Usuario_VerificarUsuario 'JADBA', 'JABA'
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Usuario_VerificarUsuario
	@cCodUsuario	VARCHAR(4),
	@cPassword		VARCHAR(MAX)
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			CREATE TABLE #Usuario
			(
				cCodUsuario		VARCHAR(4),
				cPassword		VARCHAR(MAX),
				cCargoCod		VARCHAR(40)
			)

			INSERT INTO #Usuario(cCodUsuario, cPassword, cCargoCod)
			SELECT
				T1.cCodUsuario,
				T1.cPassword,
				T3.cCargoCod
			FROM Usuarios T1
			INNER JOIN Empleado T2 WITH(NOLOCK) ON T1.nEmpleadoId = t2.nEmpleadoId
			INNER JOIN Cargo T3 WITH(NOLOCK) ON T2.nCargoId = T3.nCargoId
			WHERE T1.cCodUsuario = @cCodUsuario AND T1.cPassword = @cPassword

			SELECT cCodUsuario, cPassword, cCargoCod FROM #Usuario

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
