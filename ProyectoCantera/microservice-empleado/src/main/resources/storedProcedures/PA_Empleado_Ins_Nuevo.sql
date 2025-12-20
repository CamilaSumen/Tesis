IF OBJECT_ID('PA_Empleado_Ins_Nuevo') IS NOT NULL
    DROP PROCEDURE PA_Empleado_Ins_Nuevo
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Inserta un nuevo empleado con su usuario asociado.
					| El código de usuario se genera automáticamente con las iniciales.
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-07-05
FECHA MODIFICACIÓN	| 2025-12-19
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Empleado_Ins_Nuevo
    @cNombres = 'JUAN ALBERTO',
    @cApePaterno = 'PEREZ',
    @cApeMaterno = 'BENITEZ',
    @dFechaNacimiento = '1997-08-07',
    @cDireccion = 'COCA COLA',
    @cTelefono = '920668255',
    @cDni = '73142489',
    @cCorreo = 'JUAN@gmail.com',
    @nCargoId = 1,
    @fechaIngreso = '2025-06-05',
    @cPassword = '123456'
-- Resultado: Usuario = JABC
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Empleado_Ins_Nuevo(
    @cNombres VARCHAR(50),
    @cApePaterno VARCHAR(50),
    @cApeMaterno VARCHAR(50),
    @dFechaNacimiento DATE,
    @cDireccion VARCHAR(200),
    @cTelefono VARCHAR(9),
    @cDni VARCHAR(8),
    @cCorreo VARCHAR(200),
    @nCargoId INT,
    @fechaIngreso DATE,
    @cPassword VARCHAR(MAX)
)
AS
BEGIN
    SET NOCOUNT ON

    DECLARE @nPersonaId INT
    DECLARE @nEmpleadoId INT
    DECLARE @cCodUsuario VARCHAR(4)
    DECLARE @cCodUsuarioBase VARCHAR(4)
    DECLARE @nContador INT = 1

    BEGIN TRY
        BEGIN TRAN

            IF EXISTS(SELECT 1 FROM Persona with(nolock) WHERE cDni = @cDni)
            BEGIN
                RETURN
            END

            IF NOT EXISTS(SELECT 1 FROM Cargo with(nolock) WHERE nCargoId = @nCargoId AND bEstado = 1)
            BEGIN
                RETURN
            END


            DECLARE @NombresIniciales VARCHAR(10) = ''
            DECLARE @PalabraNombre VARCHAR(50)
            DECLARE @PosNombre INT = 1
            DECLARE @NombresTemp VARCHAR(50) = LTRIM(RTRIM(@cNombres)) + ' '

            WHILE CHARINDEX(' ', @NombresTemp) > 0
            BEGIN
                SET @PalabraNombre = LEFT(@NombresTemp, CHARINDEX(' ', @NombresTemp) - 1)
                IF LEN(@PalabraNombre) > 0
                    SET @NombresIniciales = @NombresIniciales + LEFT(@PalabraNombre, 1)
                SET @NombresTemp = SUBSTRING(@NombresTemp, CHARINDEX(' ', @NombresTemp) + 1, LEN(@NombresTemp))
            END

            SET @cCodUsuarioBase = UPPER(@NombresIniciales + LEFT(@cApePaterno, 1) + LEFT(@cApeMaterno, 1))
            SET @cCodUsuario = @cCodUsuarioBase

            WHILE EXISTS(SELECT 1 FROM Usuarios WHERE cCodUsuario = @cCodUsuario)
            BEGIN
                IF LEN(@cCodUsuarioBase) >= 4
                    SET @cCodUsuario = LEFT(@cCodUsuarioBase, 3) + CAST(@nContador AS VARCHAR(1))
                ELSE
                    SET @cCodUsuario = @cCodUsuarioBase + CAST(@nContador AS VARCHAR(1))

                SET @nContador = @nContador + 1

                IF @nContador > 9
                BEGIN
                    RETURN
                END
            END

            SET @cCodUsuario = LEFT(@cCodUsuario, 4)

            INSERT INTO Persona (cNombres, cApePaterno, cApeMaterno, dFechaNacimiento,
                               cDireccion, cTelefono, cDni, cCorreo)
            VALUES (@cNombres, @cApePaterno, @cApeMaterno, @dFechaNacimiento,
                   @cDireccion, @cTelefono, @cDni, @cCorreo)

            SET @nPersonaId = SCOPE_IDENTITY()

            INSERT INTO Empleado (nPersonaId, nCargoId, fechaIngreso, bactivo)
            VALUES (@nPersonaId, @nCargoId, @fechaIngreso, 1)

            SET @nEmpleadoId = SCOPE_IDENTITY()

            INSERT INTO Usuarios (nEmpleadoId, cCodUsuario, cPassword, nEstado)
            VALUES (@nEmpleadoId, @cCodUsuario, @cPassword, 1)


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