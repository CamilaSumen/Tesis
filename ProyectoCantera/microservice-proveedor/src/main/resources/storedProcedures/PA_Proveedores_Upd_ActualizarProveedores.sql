IF OBJECT_ID('PA_Proveedores_Upd_ActualizarProveedores') IS NOT NULL
    DROP PROCEDURE PA_Proveedores_Upd_ActualizarProveedores
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Modifica una Proveedores existente.
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Proveedores_Upd_ActualizarProveedores 1, 'sac2', '202310231', '92025633', 'coca cola'
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Proveedores_Upd_ActualizarProveedores (
	@nProveedoresId				INT,
    @nNombreProveedor		VARCHAR(100),
	@nRuc					VARCHAR(20),
	@nTelefono				VARCHAR(13),
	@nDireccion				VARCHAR(150)
)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRAN

        UPDATE Proveedores
        SET
			nNombreProveedor = @nNombreProveedor,
			nRuc = @nRuc,
			nTelefono = @nTelefono,
			nDireccion = @nDireccion
        WHERE nProveedorId = @nProveedoresId

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
