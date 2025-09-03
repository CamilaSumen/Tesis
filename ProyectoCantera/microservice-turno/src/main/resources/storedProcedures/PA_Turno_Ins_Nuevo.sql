IF OBJECT_ID('PA_Turno_Ins_Nuevo') IS NOT NULL
    DROP PROCEDURE PA_Turno_Ins_Nuevo
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Inserta un nuevo turno.
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
	EXEC PA_Turno_Ins_Nuevo 'Mañana', '06:00', '14:00'
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Turno_Ins_Nuevo (
	@cNombre		VARCHAR(50),
	@tHoraInicio	TIME,
	@tHoraFin		TIME
)
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN


			INSERT INTO Turno (cNombre, tHoraInicio, tHoraFin)
			VALUES (@cNombre, @tHoraInicio, @tHoraFin)


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
