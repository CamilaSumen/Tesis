IF OBJECT_ID('PA_Empleado_Upd_Modificar') IS NOT NULL
    DROP PROCEDURE PA_Empleado_Upd_Modificar
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Modifica un empleado (persona + datos laborales).
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-07-05
-----------------------------------------------------------------------------------

EJEMPLO:
EXEC PA_Empleado_Upd_Modificar 1, 'JORGE', 'BONIFAZ', 'CAMPOS', '1995-08-07', 'NUEVA DIRECCION', '920668255', '73146289', 'jorge@gmail.com', 3
-----------------------------------------------------------------------------------*/

CREATE PROCEDURE PA_Empleado_Upd_Modificar(
    @nEmpleadoId INT,

    @cNombres VARCHAR(50),
    @cApePaterno VARCHAR(50),
    @cApeMaterno VARCHAR(50),
    @dFechaNacimiento DATE,
    @cDireccion VARCHAR(200),
    @cTelefono VARCHAR(9),
    @cDni VARCHAR(8),
    @cCorreo VARCHAR(200),

    @nCargoId INT
)
AS
BEGIN
    SET NOCOUNT ON
    BEGIN TRY
        BEGIN TRAN

        DECLARE @nPersonaId INT
        SELECT @nPersonaId = nPersonaId FROM Empleado WHERE nEmpleadoId = @nEmpleadoId

        -- Actualizar Persona
        UPDATE Persona
        SET cNombres = @cNombres,
            cApePaterno = @cApePaterno,
            cApeMaterno = @cApeMaterno,
            dFechaNacimiento = @dFechaNacimiento,
            cDireccion = @cDireccion,
            cTelefono = @cTelefono,
            cDni = @cDni,
            cCorreo = @cCorreo
        WHERE nPersonaId = @nPersonaId

        -- Actualizar Empleado
        UPDATE Empleado
        SET nCargoId = @nCargoId
        WHERE nEmpleadoId = @nEmpleadoId

        COMMIT TRAN
    END TRY
	BEGIN CATCH
		IF @@TRANCOUNT > 0 ROLLBACK TRAN
		DECLARE @ErrorMessage NVARCHAR(4000), @ErrorSeverity INT, @ErrorState INT
		SELECT @ErrorMessage = ERROR_MESSAGE(), @ErrorSeverity = ERROR_SEVERITY(), @ErrorState = ERROR_STATE()
		RAISERROR(@ErrorMessage, @ErrorSeverity, @ErrorState)
	END CATCH
END
