IF OBJECT_ID('PA_Usuario_ListarUsuario') IS NOT NULL
    DROP PROCEDURE PA_Usuario_ListarUsuario
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Lista todos los usuarios activos.
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-07-05
EJEMPLO:
EXEC PA_Usuario_ListarUsuario
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Usuario_ListarUsuario
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			SELECT
				u.nUsuarioId,
				u.cCodUsuario,
				u.cPassword,
				u.nPrivilegioId,
				p.cNombrePrivilegio,
				u.nTurnoId,
				t.cNombre AS nombreTurno,
				u.nEmpleadoId,
				pp.cNombres,
				pp.cApePaterno,
				pp.cApeMaterno,
				pp.cDni,
				pp.cCorreo,
				pp.cTelefono,
				u.nEstado
			FROM Usuarios u
			INNER JOIN Privilegios p ON u.nPrivilegioId = p.nPrivilegiosId
			INNER JOIN Turno t ON u.nTurnoId = t.nTurnoId
			INNER JOIN Empleado pe ON u.nEmpleadoId = pe.nEmpleadoId
			INNER JOIN Persona PP ON PE.nPersonaId = pp.nPersonaId
			--WHERE u.nEstado = 1

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
