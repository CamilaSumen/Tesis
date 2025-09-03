IF OBJECT_ID('PA_CategoriaProducto_Upd_NuevaCategoria') IS NOT NULL
    DROP PROCEDURE PA_CategoriaProducto_Upd_NuevaCategoria
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Modifica un usuario existente.
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_CategoriaProducto_Upd_NuevaCategoria 1, 'NEW', 'pruebis', 'DDHASDAS.D.SA.DSA.DSA.', 1
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_CategoriaProducto_Upd_NuevaCategoria (
    @nCategoriaProductoId	INT,
    @cNombreCategoria		VARCHAR(50),
    @cDescripcion			VARCHAR(150),
    @bImagen				VARCHAR(MAX),
    @bEstado				BIT
)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRAN

        UPDATE CategoriaProducto
        SET
            cNombreCategoria = @cNombreCategoria,
            cDescripcion = @cDescripcion,
            bImagen = @bImagen,
            bEstado = @bEstado
        WHERE nCategoriaProductoId = @nCategoriaProductoId

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
