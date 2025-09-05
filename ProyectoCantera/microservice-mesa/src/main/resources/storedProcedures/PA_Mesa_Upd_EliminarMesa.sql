IF OBJECT_ID('PA_Mesa_Upd_EliminarMesa') IS NOT NULL
    DROP PROCEDURE PA_Mesa_Upd_EliminarMesa
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Eliminación lógica de la mesa (nEstado = 0).
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Mesa_Upd_EliminarMesa 1
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Mesa_Upd_EliminarMesa (
    @nMesaId		INT
)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRAN

        UPDATE Mesa
        SET bEstado = 0
        WHERE nMesaId = @nMesaId;

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
