IF OBJECT_ID('PA_Usuario_Ins_NuevoUsuario') IS NOT NULL
    DROP PROCEDURE PA_Usuario_Ins_NuevoUsuario
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Inserta un nuevo usuario.
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Usuario_Ins_NuevoUsuario 'AAPD', '123456', 1,2, 3
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Usuario_Ins_NuevoUsuario (
    @cCodUsuario VARCHAR(4),
    @cPassword VARCHAR(MAX),
    @nPrivilegioId INT,
    @nTurnoId INT,
    @nEmpleadoId INT
)
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			INSERT INTO Usuarios (
				cCodUsuario, cPassword, nPrivilegioId, nTurnoId, nEmpleadoId, nEstado
			)
			VALUES (
				@cCodUsuario, @cPassword, @nPrivilegioId, @nTurnoId, @nEmpleadoId, 1
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
