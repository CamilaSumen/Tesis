IF OBJECT_ID('PA_Usuario_Upd_Modificar') IS NOT NULL
    DROP PROCEDURE PA_Usuario_Upd_Modificar
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Modifica un usuario existente.
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-07-05
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Usuario_Upd_Modificar 1, 'JABA', 'nuevaClave', 1, 1
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Usuario_Upd_Modificar (
    @nUsuarioId INT,
    @cCodUsuario VARCHAR(4),
    @cPassword VARCHAR(MAX),
    @nPrivilegioId INT,
    @nTurnoId INT
)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRAN

        UPDATE Usuarios
        SET
            cCodUsuario = @cCodUsuario,
            cPassword = @cPassword,
            nPrivilegioId = @nPrivilegioId,
            nTurnoId = @nTurnoId
        WHERE nUsuarioId = @nUsuarioId

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
