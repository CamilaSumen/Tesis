-- =============================================
-- SP: Obtener stock actual de insumos
-- =============================================
CREATE PROCEDURE sp_obtener_stock_insumos
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        i.nInsumoId,
        i.cNombreInsumo,
        i.cUnidadMedida,
        i.nStockActual,
        ci.cNombre as Categoria,
        CASE
            WHEN i.nStockActual <= 0 THEN 'SIN STOCK'
            WHEN i.nStockActual <= 10 THEN 'STOCK BAJO'
            WHEN i.nStockActual <= 50 THEN 'STOCK MEDIO'
            ELSE 'STOCK ALTO'
        END as EstadoStock
    FROM Insumo i
    INNER JOIN CategoriaInsumo ci ON i.nCategoriaInsumoId = ci.nCategoriaInsumoId
    WHERE i.bEstado = 1
    ORDER BY ci.cNombre, i.cNombreInsumo
END