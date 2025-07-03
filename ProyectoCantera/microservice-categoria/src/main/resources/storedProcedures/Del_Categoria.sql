IF OBJECT_ID('Del_Categoria') IS NOT NULL
    DROP PROCEDURE Del_Categoria
GO

CREATE PROCEDURE Del_Categoria(
	@nCategoriaProductoId int
)
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN
			DELETE FROM CategoriaProducto
			WHERE nCategoriaProductoId = @nCategoriaProductoId
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		IF @@TRANCOUNT > 0
			ROLLBACK TRAN
		DECLARE @ErrorMessage NVARCHAR(4000);
		DECLARE @ErrorSeverity INT;
		DECLARE @ErrorState INT;

		SELECT
			@ErrorMessage = ERROR_MESSAGE(),
			@ErrorSeverity = ERROR_SEVERITY(),
			@ErrorState = ERROR_STATE();
		RAISERROR(@ErrorMessage, @ErrorSeverity, @ErrorState);
	END CATCH
END