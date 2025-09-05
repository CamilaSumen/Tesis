IF OBJECT_ID('PA_Producto_Upd_EliminarProducto') IS NOT NULL
    DROP PROCEDURE PA_Producto_Upd_EliminarProducto
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Eliminación lógica de la Producto (nEstado = 0).
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Producto_Upd_EliminarProducto 1
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Producto_Upd_EliminarProducto (
    @nProductoId		INT
)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRAN

        UPDATE Producto
        SET bEstado = 0
        WHERE nProductoId = @nProductoId;

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
