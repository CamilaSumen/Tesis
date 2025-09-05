IF OBJECT_ID('PA_Producto_Sel_ListarProducto') IS NOT NULL
    DROP PROCEDURE PA_Producto_Sel_ListarProducto
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Lista todas las Producto activas
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-09-03
EJEMPLO:
EXEC PA_Producto_Sel_ListarProducto
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Producto_Sel_ListarProducto
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			SELECT
				T1.nProductoId,
				T1.cNombreProducto,
				T1.cNombreProducto,
				T1.cDescripcionProducto,
				T2.cNombreCategoria,
				T1.nPrecio,
				T1.dFechaRegistro,
				T1.bEstado
			FROM Producto T1 WITH(NOLOCK)
			INNER JOIN CategoriaProducto T2 ON T1.nCategoriaProductoId = T2.nCategoriaProductoId

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
