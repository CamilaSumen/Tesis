IF OBJECT_ID('PA_FormaPago_Upd_FormaPago') IS NOT NULL
    DROP PROCEDURE PA_FormaPago_Upd_FormaPago
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Modifica un FormaPago existente.
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_FormaPago_Upd_FormaPago 1, 'jorge', '46465'
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_FormaPago_Upd_FormaPago (
    @nFormaPagoId				INT,
    @cNombreFormaPago			VARCHAR(3),
	@cImagen					VARCHAR(3)
)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRAN

        UPDATE FormaPago
        SET
			cNombreFormaPago = @cNombreFormaPago,
			cImagen = @cImagen
        WHERE nFormaPagoId = @nFormaPagoId

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
