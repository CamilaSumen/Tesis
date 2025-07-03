IF OBJECT_ID('Upd_Categoria') IS NOT NULL
    DROP PROCEDURE Upd_Categoria
GO

CREATE PROCEDURE Upd_Sede(
		@cDescripcion varchar (150),
		@bEstado bit,
		@nCategoriaProductoId int
)
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN
			update CategoriaProducto
			set
				cDescripcion = @cDescripcion,
				bEstado = @bEstado
			where nCategoriaProductoId = @nCategoriaProductoId
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