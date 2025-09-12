IF OBJECT_ID('PA_Adicional_Sel_ListarAdicional') IS NOT NULL
    DROP PROCEDURE PA_Adicional_Sel_ListarAdicional
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Lista todas las Adicional activas
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-09-03
EJEMPLO:
EXEC PA_Adicional_Sel_ListarAdicional
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Adicional_Sel_ListarAdicional
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			SELECT
				T1.nAdicionalId,
				T1.cNombreAdicional,
				T1.cDescripcionAdicional,
				T1.nPrecio,
				T1.cImagen,
				T1.bEstado
			FROM Adicional T1 WITH(NOLOCK)

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
