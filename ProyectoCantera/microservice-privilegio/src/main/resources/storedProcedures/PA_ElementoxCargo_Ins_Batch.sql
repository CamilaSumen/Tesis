IF OBJECT_ID('PA_ElementoxCargo_Ins_Batch') IS NOT NULL
    DROP PROCEDURE PA_ElementoxCargo_Ins_Batch
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Guarda múltiples asignaciones de elementos a un cargo
					| Elimina las anteriores y crea las nuevas
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-12-19
-----------------------------------------------------------------------------------
EJEMPLO:
-- Primero crear un tipo de tabla
CREATE TYPE ElementoListType AS TABLE (nElementoId INT)
GO

DECLARE @elementos ElementoListType
INSERT INTO @elementos VALUES (1), (2), (3)
EXEC PA_ElementoxCargo_Ins_Batch @nCargoId = 1, @elementos = @elementos
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_ElementoxCargo_Ins_Batch(
    @nCargoId INT,
    @elementosIds VARCHAR(MAX) -- IDs separados por comas: "1,2,3"
)
AS
BEGIN
    SET NOCOUNT ON

    BEGIN TRY
        BEGIN TRAN

            -- Eliminar asignaciones previas
            DELETE FROM ElementoxCargo
            WHERE nCargoId = @nCargoId

            -- Insertar nuevas asignaciones
            INSERT INTO ElementoxCargo (nElementoId, nCargoId)
            SELECT
                CAST(value AS INT),
                @nCargoId
            FROM STRING_SPLIT(@elementosIds, ',')
            WHERE TRIM(value) != ''

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