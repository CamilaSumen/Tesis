IF OBJECT_ID('PA_Proveedores_Ins_NuevaProveedores') IS NOT NULL
    DROP PROCEDURE PA_Proveedores_Ins_NuevaProveedores
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Inserta una nueva Proveedores.
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-09-03
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Proveedores_Ins_NuevaProveedores 'sac', '202310231', '92025633', 'coca cola'
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Proveedores_Ins_NuevaProveedores (
    @nNombreProveedor		VARCHAR(100),
	@nRuc					VARCHAR(20),
	@nTelefono				VARCHAR(13),
	@nDireccion				VARCHAR(150)
)
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			INSERT INTO Proveedores (
				nNombreProveedor, nRuc, nTelefono, nDireccion
			)
			VALUES (
				@nNombreProveedor, @nRuc, @nTelefono, @nDireccion
			)


		COMMIT TRAN
	END TRY
	BEGIN CATCH
		IF @@TRANCOUNT > 0
			ROLLBACK TRAN
		DECLARE @ErrorMessage NVARCHAR(4000)
		DECLARE @ErrorSeverity INT
		DECLARE @ErrorState INT

		SELECT
			@ErrorMessage = ERROR_MESSAGE(),
			@ErrorSeverity = ERROR_SEVERITY(),
			@ErrorState = ERROR_STATE()
		RAISERROR(@ErrorMessage, @ErrorSeverity, @ErrorState)
	END CATCH
END
