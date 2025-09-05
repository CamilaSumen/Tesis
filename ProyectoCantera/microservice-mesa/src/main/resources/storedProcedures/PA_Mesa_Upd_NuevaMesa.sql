IF OBJECT_ID('PA_Mesa_Upd_NuevaMesa') IS NOT NULL
    DROP PROCEDURE PA_Mesa_Upd_NuevaMesa
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Modifica una mesa existente.
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Mesa_Upd_NuevaMesa 1, '03'
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Mesa_Upd_NuevaMesa (
    @nMesaId					INT,
    @cCodMesa					VARCHAR(3)
)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRAN

        UPDATE Mesa
        SET
            cCodMesa = @cCodMesa
        WHERE nMesaId = @nMesaId

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
