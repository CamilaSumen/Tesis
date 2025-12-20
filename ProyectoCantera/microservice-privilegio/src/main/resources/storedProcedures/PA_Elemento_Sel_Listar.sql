IF OBJECT_ID('PA_Elemento_Sel_Listar') IS NOT NULL
    DROP PROCEDURE PA_Elemento_Sel_Listar
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Lista todos los elementos/interfaces del sistema
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-12-19
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Elemento_Sel_Listar
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Elemento_Sel_Listar
AS
BEGIN
    SET NOCOUNT ON

    SELECT
        nElementoId,
        cCodElmento,
        cModulo,
        cNombreElemento,
        cComandoElmento
    FROM Elemento WITH(NOLOCK)
    ORDER BY cModulo, cNombreElemento
END
GO