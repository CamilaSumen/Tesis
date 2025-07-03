IF OBJECT_ID('Sel_Categoria_ID') IS NOT NULL
    DROP PROCEDURE Sel_Categoira_ID
GO

CREATE PROCEDURE Sel_Categoria_ID(
    @nCategoriaProductoId int
)
AS
BEGIN
	Select cp.nCategoriaProductoId, cp.cDescripcion, cp.bEstado
	from CategoriaProducto cp
	where cp.nCategoriaProductoId = @nCategoriaProductoId
END