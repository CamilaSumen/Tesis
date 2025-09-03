IF OBJECT_ID('PA_Turno_Upd_Modificar') IS NOT NULL
    DROP PROCEDURE PA_Turno_Upd_Modificar
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Modifica los datos de un turno.
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
	EXEC PA_Turno_Upd_Modificar 1, 'Tarde', '14:00', '22:00'
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Turno_Upd_Modificar (
	@nTurnoId			INT,
	@cNombre			VARCHAR(50),
	@tHoraInicio		TIME,
	@tHoraFin			TIME
)
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN


			UPDATE Turno
			SET cNombre = @cNombre,
				tHoraInicio = @tHoraInicio,
				tHoraFin = @tHoraFin
			WHERE nTurnoId = @nTurnoId


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
