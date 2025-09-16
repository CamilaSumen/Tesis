IF OBJECT_ID('PA_Categoriainsumo_Ins_NuevaCategoriainsumo') IS NOT NULL
    DROP PROCEDURE PA_Categoriainsumo_Ins_NuevaCategoriainsumo
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Inserta una nueva Categoriainsumo.
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-09-03
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Categoriainsumo_Ins_NuevaCategoriainsumo 'cate insumo 1', 'descripcion cate 1'
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Categoriainsumo_Ins_NuevaCategoriainsumo (
    @cNombre				VARCHAR(100),
	@cDescripcion			VARCHAR(200),
	@cImagen				VARCHAR(MAX)
)
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			INSERT INTO Categoriainsumo (
				cNombre, cDescripcion, cImagen
			)
			VALUES (
				@cNombre, @cDescripcion, @cImagen
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
