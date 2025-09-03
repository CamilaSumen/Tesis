IF OBJECT_ID('PA_CategoriaProducto_Ins_NuevaCategoria') IS NOT NULL
    DROP PROCEDURE PA_CategoriaProducto_Ins_NuevaCategoria
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Inserta una nueva categoria.
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-09-03
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_CategoriaProducto_Ins_NuevaCategoria 'POLLO1', 'pollos', '............'
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_CategoriaProducto_Ins_NuevaCategoria (
    @cNombreCategoria		VARCHAR(50),
	@cDescripcion			VARCHAR(150),
    @bImagen				VARCHAR(MAX)
)
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			INSERT INTO CategoriaProducto (
				cNombreCategoria, cDescripcion,bImagen
			)
			VALUES (
				@cNombreCategoria, @cDescripcion, @bImagen
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
