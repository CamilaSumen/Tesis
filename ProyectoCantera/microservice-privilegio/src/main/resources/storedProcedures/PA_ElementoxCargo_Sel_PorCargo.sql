IF OBJECT_ID('PA_ElementoxCargo_Sel_PorCargo') IS NOT NULL
    DROP PROCEDURE PA_ElementoxCargo_Sel_PorCargo
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Lista los elementos asignados a un cargo específico
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-12-19
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_ElementoxCargo_Sel_PorCargo @nCargoId = 1
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_ElementoxCargo_Sel_PorCargo(
    @nCargoId INT
)
AS
BEGIN
    SET NOCOUNT ON

    SELECT
        ec.nElementoxCargoId,
        ec.nElementoId,
        ec.nCargoId,
        e.cCodElmento,
        e.cModulo,
        e.cNombreElemento,
        e.cComandoElmento
    FROM ElementoxCargo ec WITH(NOLOCK)
    INNER JOIN Elemento e WITH(NOLOCK) ON ec.nElementoId = e.nElementoId
    WHERE ec.nCargoId = @nCargoId
    ORDER BY e.cModulo, e.cNombreElemento
END
GO
