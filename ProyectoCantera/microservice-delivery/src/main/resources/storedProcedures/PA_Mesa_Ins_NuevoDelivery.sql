IF OBJECT_ID('PA_Mesa_Ins_NuevoDelivery') IS NOT NULL
    DROP PROCEDURE PA_Mesa_Ins_NuevoDelivery
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Inserta un nuevo delivery
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-09-03
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Mesa_Ins_NuevoDelivery 'arkha', '9520668255'
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Mesa_Ins_NuevoDelivery (
    @cNombre				VARCHAR(100),
	@cTelefono				VARCHAR(15)
)
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			INSERT INTO Delivery(
				cNombre, cTelefono
			)
			VALUES (
				@cNombre, @cTelefono
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
