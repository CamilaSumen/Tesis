IF OBJECT_ID('Ins_Categoria') IS NOT NULL
    DROP PROCEDURE Ins_Categoria
GO


CREATE PROCEDURE Ins_Categoria(
	@cDescripcion varchar (150),
	@bEstado bit


)
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN
			INSERT INTO CategoriaProducto(cDescripcion, bEstado)
			values(@cDescripcion, @bEstado)
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