IF OBJECT_ID('PA_Insumo_Upd_EliminarInsumo') IS NOT NULL
    DROP PROCEDURE PA_Insumo_Upd_EliminarInsumo
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Eliminación lógica de la Insumo (nEstado = 0).
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Insumo_Upd_EliminarInsumo 6
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Insumo_Upd_EliminarInsumo (
    @nInsumoId		INT
)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRAN

        UPDATE Insumo
        SET bEstado = 0
        WHERE nInsumoId = @nInsumoId;

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
