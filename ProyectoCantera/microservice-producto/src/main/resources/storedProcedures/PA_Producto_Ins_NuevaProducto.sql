IF OBJECT_ID('PA_Producto_Ins_NuevaProducto') IS NOT NULL
    DROP PROCEDURE PA_Producto_Ins_NuevaProducto
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Inserta una nueva Producto.
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-09-03
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Producto_Ins_NuevaProducto 1, 'producto 1', 'descripcion 1', 'imagenbase64', 20
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Producto_Ins_NuevaProducto (
    @nCategoriaProductoId			INT,
	@cNombreProducto				VARCHAR(50),
	@cDescripcionProducto			VARCHAR(150),
	@cImagen						VARCHAR(MAX),
	@nPrecio						MONEY

)
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			INSERT INTO Producto (
				nCategoriaProductoId, cNombreProducto, cDescripcionProducto, cImagen, nPrecio
			)
			VALUES (
				@nCategoriaProductoId, @cNombreProducto, @cDescripcionProducto, @cImagen, @nPrecio
			)


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
