IF OBJECT_ID('PA_Empleado_Upd_Modificar') IS NOT NULL
    DROP PROCEDURE PA_Empleado_Upd_Modificar
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Actualiza los datos de un empleado existente.
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-12-19
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Empleado_Upd_Modificar
    @nEmpleadoId = 1,
    @cNombres = 'JORGE ARMANDO',
    @cApePaterno = 'BONIFAZ',
    @cApeMaterno = 'CAMPOS',
    @dFechaNacimiento = '1997-08-07',
    @cDireccion = 'AV. PRINCIPAL 123',
    @cTelefono = '920668255',
    @cDni = '73146289',
    @cCorreo = 'jorge.updated@gmail.com',
    @nCargoId = 2
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

    DECLARE @nPersonaId INT

    BEGIN TRY
        BEGIN TRAN

            -- Validar que el empleado existe
            IF NOT EXISTS(SELECT 1 FROM Empleado WHERE nEmpleadoId = @nEmpleadoId)
            BEGIN
                RAISERROR('El empleado no existe.', 16, 1)
                RETURN
            END

            -- Obtener el ID de la persona asociada
            SELECT @nPersonaId = nPersonaId
            FROM Empleado
            WHERE nEmpleadoId = @nEmpleadoId

            -- Validar que el DNI no exista en otro registro
            IF EXISTS(SELECT 1 FROM Persona
                     WHERE cDni = @cDni
                     AND nPersonaId != @nPersonaId)
            BEGIN
                RAISERROR('El DNI ya está registrado en otro empleado.', 16, 1)
                RETURN
            END

            -- Validar que el cargo existe y está activo
            IF NOT EXISTS(SELECT 1 FROM Cargo WHERE nCargoId = @nCargoId AND bEstado = 1)
            BEGIN
                RAISERROR('El cargo especificado no existe o está inactivo.', 16, 1)
                RETURN
            END

            -- Actualizar datos de Persona
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

            -- Actualizar cargo del empleado
            UPDATE Empleado
            SET nCargoId = @nCargoId
            WHERE nEmpleadoId = @nEmpleadoId

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