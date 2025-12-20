IF OBJECT_ID('PA_Elemento_Sel_Disponibles') IS NOT NULL
    DROP PROCEDURE PA_Elemento_Sel_Disponibles
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Lista elementos que NO están asignados a un cargo
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-12-19
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Elemento_Sel_Disponibles @nCargoId = 1
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Elemento_Sel_Disponibles(
    @nCargoId INT
)
AS
BEGIN
    SET NOCOUNT ON

    SELECT
        e.nElementoId,
        e.cCodElmento,
        e.cModulo,
        e.cNombreElemento,
        e.cComandoElmento
    FROM Elemento e WITH(NOLOCK)
    WHERE e.nElementoId NOT IN (
        SELECT nElementoId
        FROM ElementoxCargo WITH(NOLOCK)
        WHERE nCargoId = @nCargoId
    )
    ORDER BY e.cModulo, e.cNombreElemento
END
GO