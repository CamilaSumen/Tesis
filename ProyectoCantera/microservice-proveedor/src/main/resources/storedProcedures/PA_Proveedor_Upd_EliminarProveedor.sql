IF OBJECT_ID('PA_Proveedor_Upd_EliminarProveedor') IS NOT NULL
    DROP PROCEDURE PA_Proveedor_Upd_EliminarProveedor
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Eliminación lógica de la Proveedor (nEstado = 0).
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Proveedor_Upd_EliminarProveedor 1
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Proveedor_Upd_EliminarProveedor (
    @nProveedorId		INT
)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRAN

			UPDATE Proveedores
			SET bEstado = 0
			WHERE nProveedorId = @nProveedorId;

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
