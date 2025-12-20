IF OBJECT_ID('PA_ElementoxCargo_Ins_Nuevo') IS NOT NULL
    DROP PROCEDURE PA_ElementoxCargo_Ins_Nuevo
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Asigna un elemento a un cargo
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-12-19
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_ElementoxCargo_Ins_Nuevo @nElementoId = 1, @nCargoId = 1
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_ElementoxCargo_Ins_Nuevo(
    @nElementoId INT,
    @nCargoId INT
)
AS
BEGIN
    SET NOCOUNT ON

    BEGIN TRY
        BEGIN TRAN

            -- Validar que el elemento existe
            IF NOT EXISTS(SELECT 1 FROM Elemento WHERE nElementoId = @nElementoId)
            BEGIN
                RAISERROR('El elemento no existe.', 16, 1)
                RETURN
            END

            -- Validar que el cargo existe
            IF NOT EXISTS(SELECT 1 FROM Cargo WHERE nCargoId = @nCargoId)
            BEGIN
                RAISERROR('El cargo no existe.', 16, 1)
                RETURN
            END

            -- Validar que no exista la asignación
            IF EXISTS(SELECT 1 FROM ElementoxCargo
                     WHERE nElementoId = @nElementoId
                     AND nCargoId = @nCargoId)
            BEGIN
                RAISERROR('El elemento ya está asignado a este cargo.', 16, 1)
                RETURN
            END

            -- Insertar la asignación
            INSERT INTO ElementoxCargo (nElementoId, nCargoId)
            VALUES (@nElementoId, @nCargoId)

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
