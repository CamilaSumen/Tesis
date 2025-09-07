IF OBJECT_ID('PA_Categoriainsumo_Upd_EliminarCategoriainsumo') IS NOT NULL
    DROP PROCEDURE PA_Categoriainsumo_Upd_EliminarCategoriainsumo
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Eliminación lógica de la Categoriainsumo (nEstado = 0).
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Categoriainsumo_Upd_EliminarCategoriainsumo 1
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Categoriainsumo_Upd_EliminarCategoriainsumo (
    @nCategoriainsumoId		INT
)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRAN

        UPDATE Categoriainsumo
        SET bEstado = 0
        WHERE nCategoriainsumoId = @nCategoriainsumoId;

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
