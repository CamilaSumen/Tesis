IF OBJECT_ID('PA_Categoriainsumo_Upd_ActualizarCategoriainsumo') IS NOT NULL
    DROP PROCEDURE PA_Categoriainsumo_Upd_ActualizarCategoriainsumo
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Modifica una Categoriainsumo existente.
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Categoriainsumo_Upd_ActualizarCategoriainsumo 1, 'DSADSA', 'DSADSADSA'
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Categoriainsumo_Upd_ActualizarCategoriainsumo (
    @nCategoriainsumoId					INT,
    @cNombre							VARCHAR(100),
	@cDescripcion						VARCHAR(200),
	@cImagen							VARCHAR(MAX)
)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRAN

        UPDATE Categoriainsumo
        SET
            cNombre = @cNombre,
			cDescripcion = @cDescripcion,
			cImagen = @cImagen
        WHERE nCategoriainsumoId = @nCategoriainsumoId

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
