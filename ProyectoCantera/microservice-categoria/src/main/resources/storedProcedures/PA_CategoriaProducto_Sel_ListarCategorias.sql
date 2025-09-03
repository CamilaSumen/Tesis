IF OBJECT_ID('PA_CategoriaProducto_Sel_ListarCategorias') IS NOT NULL
    DROP PROCEDURE PA_CategoriaProducto_Sel_ListarCategorias
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Lista todas las categorias activas
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-09-03
EJEMPLO:
EXEC PA_CategoriaProducto_Sel_ListarCategorias
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_CategoriaProducto_Sel_ListarCategorias
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			SELECT
				T1.nCategoriaProductoId,
				T1.cNombreCategoria,
				T1.cDescripcion,
				T1.bImagen,
				T1.bEstado
			FROM CategoriaProducto T1

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
