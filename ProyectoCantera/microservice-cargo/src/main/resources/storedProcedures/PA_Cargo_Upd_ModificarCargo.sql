IF OBJECT_ID('PA_Empleado_Sel_Listar') IS NOT NULL
    DROP PROCEDURE PA_Empleado_Sel_Listar
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Lista todos los empleados activos con sus datos.
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-07-05
-----------------------------------------------------------------------------------

EJEMPLO:
EXEC PA_Empleado_Sel_Listar
-----------------------------------------------------------------------------------*/

CREATE PROCEDURE PA_Empleado_Sel_Listar
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			SELECT
				e.nEmpleadoId,
				p.nPersonaId,
				p.cNombres,
				p.cApePaterno,
				p.cApeMaterno,
				p.cDni,
				p.cCorreo,
				p.cTelefono,
				p.dFechaNacimiento,
				p.cDireccion,
				e.nCargoId,
				c.cNombreCargo,
				e.fechaIngreso,
				c.nSueldo,
				e.bactivo
			FROM Empleado e WITH(NOLOCK)
			INNER JOIN Persona p WITH(NOLOCK) ON e.nPersonaId = p.nPersonaId
			INNER JOIN Cargo c WITH(NOLOCK) ON e.nCargoId = c.nCargoId
			--WHERE e.bactivo = 1
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