IF OBJECT_ID('PA_Privilegios_Upd_Modificar') IS NOT NULL
    DROP PROCEDURE PA_Privilegios_Upd_Modificar
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Modifica un privilegio existente.
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Privilegios_Upd_Modificar 1, 'ADMIN', 'TIENE ACCESO A TODO creo', 'Privilegio PARA PERSONAL AUTORIZADO'

-----------------------------------------------------------------------------------*/

CREATE PROCEDURE PA_Privilegios_Upd_Modificar(
	@nPrivilegiosId INT,
	@cNombrePrivilegio VARCHAR(50),
	@cDescripcion VARCHAR(200),
	@cObservacion VARCHAR(200)
)
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

		UPDATE Privilegios
		SET
			cNombrePrivilegio = @cNombrePrivilegio,
			cDescripcion = @cDescripcion,
			cObservacion = @cObservacion
		WHERE nPrivilegiosId = @nPrivilegiosId

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
