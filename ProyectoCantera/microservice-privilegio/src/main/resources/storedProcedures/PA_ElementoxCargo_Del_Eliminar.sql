IF OBJECT_ID('PA_ElementoxCargo_Del_Eliminar') IS NOT NULL
    DROP PROCEDURE PA_ElementoxCargo_Del_Eliminar
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Elimina la asignación de un elemento a un cargo
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-12-19
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_ElementoxCargo_Del_Eliminar @nElementoxCargoId = 1
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_ElementoxCargo_Del_Eliminar(
    @nElementoxCargoId INT
)
AS
BEGIN
    SET NOCOUNT ON

    BEGIN TRY
        BEGIN TRAN

            -- Validar que existe
            IF NOT EXISTS(SELECT 1 FROM ElementoxCargo WHERE nElementoxCargoId = @nElementoxCargoId)
            BEGIN
                RAISERROR('La asignación no existe.', 16, 1)
                RETURN
            END

            -- Eliminar
            DELETE FROM ElementoxCargo
            WHERE nElementoxCargoId = @nElementoxCargoId

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
GO