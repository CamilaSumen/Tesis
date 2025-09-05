IF OBJECT_ID('PA_Adicional_Upd_Adicional') IS NOT NULL
    DROP PROCEDURE PA_Adicional_Upd_Adicional
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Modifica una Adicional existente.
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Adicional_Upd_Adicional 1, 'HOLI', 'DSAD', 30
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Adicional_Upd_Adicional (
    @nAdicionalId					INT,
	@cNombreAdicional				VARCHAR(50),
	@cDescripcionAdicional			VARCHAR(150),
	@nPrecio						MONEY
)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRAN

        UPDATE Adicional
        SET
            cNombreAdicional = @cNombreAdicional,
			cDescripcionAdicional = @cDescripcionAdicional,
			nPrecio = @nPrecio
        WHERE nAdicionalId = @nAdicionalId

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
