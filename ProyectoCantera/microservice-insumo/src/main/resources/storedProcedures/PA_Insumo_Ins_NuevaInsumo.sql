IF OBJECT_ID('PA_Insumo_Ins_NuevaInsumo') IS NOT NULL
    DROP PROCEDURE PA_Insumo_Ins_NuevaInsumo
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Inserta una nueva Insumo.
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-09-03
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Insumo_Ins_NuevaInsumo 1, 'insumo 1', 'kg', 0
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Insumo_Ins_NuevaInsumo (
    @nCategoriaInsumoId		INT,
	@cNombreInsumo			VARCHAR(100),
	@cUnidadMedida			VARCHAR(20),
	@nStockActual			DECIMAL(10,2),
	@cImagen				VARCHAR(MAX)
)
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			INSERT INTO Insumo (
				nCategoriaInsumoId, cNombreInsumo, cUnidadMedida, nStockActual, cImagen
			)
			VALUES (
				@nCategoriaInsumoId, @cNombreInsumo, @cUnidadMedida, @nStockActual, @cImagen
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
