IF OBJECT_ID('PA_Turno_Sel_Listar') IS NOT NULL
    DROP PROCEDURE PA_Turno_Sel_Listar
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Lista todos los turnos activos.
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
	EXEC PA_Turno_Sel_Listar
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Turno_Sel_Listar
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

			SELECT
				T1.nTurnoId,
				T1.cNombre,
				T1.tHoraInicio,
				T1.tHoraFin,
				T1.bEstado
			FROM Turno T1 WITH(NOLOCK)


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
