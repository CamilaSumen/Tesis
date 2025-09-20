-- 3. SP para obtener insumos con stock crítico
CREATE PROCEDURE sp_obtener_stock_critico
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        i.nInsumoId as InsumoId,
        i.cNombreInsumo as NombreInsumo,
        i.nStockActual as StockActual,
        i.nStockMinimo as StockMinimo,
        i.cUnidadMedida as UnidadMedida,
        ci.cNombre as Categoria
    FROM Insumo i WITH(NOLOCK)
    INNER JOIN CategoriaInsumo ci WITH(NOLOCK) ON i.nCategoriaInsumoId = ci.nCategoriaInsumoId
    WHERE i.nStockActual <= i.nStockMinimo
        AND i.bEstado = 1
    ORDER BY (i.nStockActual - i.nStockMinimo) ASC;
END;
