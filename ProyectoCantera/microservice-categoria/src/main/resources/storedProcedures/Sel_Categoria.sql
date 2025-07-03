IF OBJECT_ID('Sel_Categoria') IS NOT NULL
    DROP PROCEDURE Sel_Categoria
GO

CREATE PROCEDURE Sel_Categoria
AS
BEGIN
	Select  cp.nCategoriaProductoId, cp.cDescripcion, cp.bEstado
	from CategoriaProducto cp
END