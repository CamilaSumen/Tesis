IF OBJECT_ID('PA_Insumo_Sel_ListarInsumo') IS NOT NULL
    DROP PROCEDURE PA_Insumo_Sel_ListarInsumo
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Lista todas las Insumo activas
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-09-03
EJEMPLO:
EXEC PA_Insumo_Sel_ListarInsumo
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Insumo_Sel_ListarInsumo
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			SELECT
				T1.nInsumoId,
				T1.cNombreInsumo,
				T2.cNombre AS cNombreCategoriaInsumo,
				T1.cUnidadMedida,
				T1.nStockActual,
				T1.bEstado
			FROM Insumo T1 WITH(NOLOCK)
			INNER JOIN Categoriainsumo T2 WITH(NOLOCK) ON T1.nCategoriaInsumoId = T2.nCategoriaInsumoId

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
