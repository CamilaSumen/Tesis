IF OBJECT_ID('PA_Empleado_Ins_Nuevo') IS NOT NULL
    DROP PROCEDURE PA_Empleado_Ins_Nuevo
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Inserta un nuevo empleado.
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-07-05
-----------------------------------------------------------------------------------

EJEMPLO:
EXEC PA_Empleado_Ins_Nuevo 'JORGE', 'BONIFAZ', 'CAMPOS', '0205-08-07', 'COCA COLA', '920668255', '73146289', 'jorge@gmail.com', '1', '2025-06-05'
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
    @fechaIngreso DATE
)
AS
BEGIN
    SET NOCOUNT ON
    BEGIN TRY
        BEGIN TRAN


			-- Insertar en Persona
			INSERT INTO Persona (cNombres, cApePaterno, cApeMaterno, dFechaNacimiento, cDireccion, cTelefono, cDni, cCorreo)
			VALUES (@cNombres, @cApePaterno, @cApeMaterno, @dFechaNacimiento, @cDireccion, @cTelefono, @cDni, @cCorreo)

			DECLARE @nPersonaId INT = SCOPE_IDENTITY()

			-- Insertar en Empleado
			INSERT INTO Empleado (nPersonaId, nCargoId, fechaIngreso, bactivo)
			VALUES (@nPersonaId, @nCargoId, @fechaIngreso, 1)


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
