IF OBJECT_ID('PA_Usuario_Upd_Eliminar') IS NOT NULL
    DROP PROCEDURE PA_Usuario_Upd_Eliminar
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Eliminación lógica del usuario (nEstado = 0).
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Usuario_Upd_Eliminar 1
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Usuario_Upd_Eliminar (
    @nUsuarioId INT
)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRAN

        UPDATE Usuarios
        SET nEstado = 0
        WHERE nUsuarioId = @nUsuarioId;

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
