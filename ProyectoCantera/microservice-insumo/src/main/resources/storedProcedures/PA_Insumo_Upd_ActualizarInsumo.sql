IF OBJECT_ID('PA_Insumo_Upd_ActualizarInsumo') IS NOT NULL
    DROP PROCEDURE PA_Insumo_Upd_ActualizarInsumo
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Modifica una Insumo existente.
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Insumo_Upd_ActualizarInsumo 6, 2, 'insumo 1', 'UNI' , 20
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Insumo_Upd_ActualizarInsumo (
	@nInsumoId				INT,
    @nCategoriaInsumoId		INT,
	@cNombreInsumo			VARCHAR(100),
	@cUnidadMedida			VARCHAR(20),
	@nStockActual			DECIMAL(10,2),
	@cImagen				VARCHAR(MAX)
)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRAN

        UPDATE Insumo
        SET
			nCategoriaInsumoId = @nCategoriaInsumoId,
			cNombreInsumo = @cNombreInsumo,
			cUnidadMedida = @cUnidadMedida,
			nStockActual = @nStockActual,
			cImagen = @cImagen
        WHERE nInsumoId = @nInsumoId

        COMMIT TRAN;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0 ROLLBACK TRAN;
        DECLARE @ErrorMessage NVARCHAR(4000), @ErrorSeverity INT, @ErrorState INT;
        SELECT
            @ErrorMessage = ERROR_MESSAGE(),
            @ErrorSeverity = ERROR_SEVERITY(),
            @ErrorState = ERROR_STATE();
        RAISERROR(@ErrorMessage, @ErrorSeverity, @ErrorState);
    END CATCH
END
GO
