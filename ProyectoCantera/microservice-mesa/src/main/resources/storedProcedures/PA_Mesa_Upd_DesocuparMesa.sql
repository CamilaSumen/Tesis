IF OBJECT_ID('PA_Mesa_Upd_DesocuparMesa') IS NOT NULL
    DROP PROCEDURE PA_Mesa_Upd_DesocuparMesa
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Modifica una mesa existente.
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Mesa_Upd_DesocuparMesa '02'
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Mesa_Upd_DesocuparMesa (
    @cCodMesa					VARCHAR(3)
)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRAN

        UPDATE Mesa
        SET
            bocupado = 0
        WHERE ccodmesa = @cCodMesa

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
