IF OBJECT_ID('PA_ElementoxCargo_Del_PorElementoCargo') IS NOT NULL
    DROP PROCEDURE PA_ElementoxCargo_Del_PorElementoCargo
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Elimina asignación por elemento y cargo
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-12-19
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_ElementoxCargo_Del_PorElementoCargo @nElementoId = 1, @nCargoId = 1
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_ElementoxCargo_Del_PorElementoCargo(
    @nElementoId INT,
    @nCargoId INT
)
AS
BEGIN
    SET NOCOUNT ON

    BEGIN TRY
        BEGIN TRAN

            DELETE FROM ElementoxCargo
            WHERE nElementoId = @nElementoId
            AND nCargoId = @nCargoId

        COMMIT TRAN
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRAN

        DECLARE @ErrorMessage NVARCHAR(4000)
        SELECT @ErrorMessage = ERROR_MESSAGE()
        RAISERROR(@ErrorMessage, 16, 1)
    END CATCH
END
GO