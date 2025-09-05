IF OBJECT_ID('PA_Producto_Upd_Producto') IS NOT NULL
    DROP PROCEDURE PA_Producto_Upd_Producto
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Modifica una Producto existente.
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Producto_Upd_Producto 1, 2, 'producto 1', 'descripcion 1', 'imagenbase64', 20
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Producto_Upd_Producto (
    @nProductoId					INT,
	@nCategoriaProductoId			INT,
	@cNombreProducto				VARCHAR(50),
	@cDescripcionProducto			VARCHAR(150),
	@cImagen						VARCHAR(MAX),
	@nPrecio						MONEY
)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRAN

        UPDATE Producto
        SET
            nCategoriaProductoId = @nCategoriaProductoId,
			cNombreProducto = @cNombreProducto,
			cDescripcionProducto = @nPrecio,
			cImagen = @cImagen,
			nPrecio = @nPrecio
        WHERE nProductoId = @nProductoId

        COMMIT TRAN;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0 ROLLBACK TRAN;
        DECLARE @ErrorMessage NVARCHAR(4000), @ErrorSeverity INT, @ErrorState INT;
        SELECT
            @ErrorMessage = ERROR_MESSAGE(),
            @ErrorSeverity = ERROR_SEVERITY(),
            @ErrorState = ERROR_STATE();
        RAISERROR(@ErrorMessage, @ErrorSeverity, @ErrorState);
    END CATCH
END
GO
