IF OBJECT_ID('PA_Cliente_Sel_Listar') IS NOT NULL
    DROP PROCEDURE PA_Cliente_Sel_Listar
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Lista todos los Clientes activos con sus datos.
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-07-05
-----------------------------------------------------------------------------------

EJEMPLO:
EXEC PA_Cliente_Sel_Listar 23275731
-----------------------------------------------------------------------------------*/

CREATE PROCEDURE PA_Cliente_Sel_Listar(
	@dni		VARCHAR(20)
)

AS
BEGIN
    SET NOCOUNT ON

    SELECT
        dni,
		nombre,
		apellido,
		email,
		telefono,
		FechaRegistro
    FROM Clientes with(nolock)
    WHERE DNI = @dni
END
