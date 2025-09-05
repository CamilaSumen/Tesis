IF OBJECT_ID('PA_Adicional_Ins_NuevaAdicional') IS NOT NULL
    DROP PROCEDURE PA_Adicional_Ins_NuevaAdicional
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Inserta una nueva Adicional.
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-09-03
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Adicional_Ins_NuevaAdicional 'DSADSA', 'DSADSADSA', 10.20
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Adicional_Ins_NuevaAdicional (
    @cNombreAdicional				VARCHAR(50),
	@cDescripcionAdicional			VARCHAR(150),
	@nPrecio						MONEY

)
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			INSERT INTO Adicional (
				cNombreAdicional, cDescripcionAdicional, nPrecio
			)
			VALUES (
				@cNombreAdicional, @cDescripcionAdicional, @nPrecio
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
