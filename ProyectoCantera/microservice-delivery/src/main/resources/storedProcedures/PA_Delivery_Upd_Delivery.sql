IF OBJECT_ID('PA_Delivery_Upd_Delivery') IS NOT NULL
    DROP PROCEDURE PA_Delivery_Upd_Delivery
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Modifica un delivery existente.
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Delivery_Upd_Delivery 1, 'jorge', '46465'
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Delivery_Upd_Delivery (
    @nDeliveryId				INT,
    @cNombre					VARCHAR(3),
	@cTelefono					VARCHAR(3)
)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRAN

        UPDATE Delivery
        SET
			cNombre = @cNombre,
			cTelefono = @cTelefono
        WHERE nDeliveryId = @nDeliveryId

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
