IF OBJECT_ID('PA_Proveedores_Sel_ListarProveedores') IS NOT NULL
    DROP PROCEDURE PA_Proveedores_Sel_ListarProveedores
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Lista todas las Proveedores activas
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-09-03
EJEMPLO:
EXEC PA_Proveedores_Sel_ListarProveedores
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Proveedores_Sel_ListarProveedores
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			SELECT
				T1.nProveedorId,
				T1.nNombreProveedor,
				T1.nRuc,
				T1.nTelefono,
				T1.nDireccion,
				T1.bEstado
			FROM Proveedores T1 WITH(NOLOCK)

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
