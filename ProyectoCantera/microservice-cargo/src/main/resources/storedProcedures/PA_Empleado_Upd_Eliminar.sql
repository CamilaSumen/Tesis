IF OBJECT_ID('PA_Empleado_Upd_Eliminar') IS NOT NULL
    DROP PROCEDURE PA_Empleado_Upd_Eliminar
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Elimina lógicamente un empleado (activo = 0).
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-07-05
-----------------------------------------------------------------------------------

EJEMPLO:
EXEC PA_Empleado_Upd_Eliminar 1
-----------------------------------------------------------------------------------*/

CREATE PROCEDURE PA_Empleado_Upd_Eliminar(
    @nEmpleadoId INT
)
AS
BEGIN
    SET NOCOUNT ON
    BEGIN TRY
        BEGIN TRAN

        UPDATE Empleado
        SET bactivo = 0
        WHERE nEmpleadoId = @nEmpleadoId

        COMMIT TRAN
    END TRY
	BEGIN CATCH
		IF @@TRANCOUNT > 0 ROLLBACK TRAN
		DECLARE @ErrorMessage NVARCHAR(4000), @ErrorSeverity INT, @ErrorState INT
		SELECT @ErrorMessage = ERROR_MESSAGE(), @ErrorSeverity = ERROR_SEVERITY(), @ErrorState = ERROR_STATE()
		RAISERROR(@ErrorMessage, @ErrorSeverity, @ErrorState)
	END CATCH
END
