IF OBJECT_ID('PA_Categoriainsumo_Sel_ListarCategoriainsumo') IS NOT NULL
    DROP PROCEDURE PA_Categoriainsumo_Sel_ListarCategoriainsumo
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Lista todas las Categoriainsumo activas
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-09-03
EJEMPLO:
EXEC PA_Categoriainsumo_Sel_ListarCategoriainsumo
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Categoriainsumo_Sel_ListarCategoriainsumo
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			SELECT
				T1.nCategoriainsumoId,
				T1.cNombre,
				T1.cDescripcion,
				T1.cImagen,
				T1.bEstado
			FROM Categoriainsumo T1 WITH(NOLOCK)

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
