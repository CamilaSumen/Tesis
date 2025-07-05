IF OBJECT_ID('PA_Empleado_Sel_Listar') IS NOT NULL
    DROP PROCEDURE PA_Empleado_Sel_Listar
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Lista todos los empleados activos con sus datos.
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-07-05
-----------------------------------------------------------------------------------

EJEMPLO:
EXEC PA_Empleado_Sel_Listar
-----------------------------------------------------------------------------------*/

CREATE PROCEDURE PA_Empleado_Sel_Listar
AS
BEGIN
    SET NOCOUNT ON

    SELECT
        e.nEmpleadoId,
        p.nPersonaId,
        p.cNombres,
        p.cApePaterno,
        p.cApeMaterno,
        p.cDni,
        p.cCorreo,
        p.cTelefono,
        p.dFechaNacimiento,
        p.cDireccion,
        e.nCargoId,
        c.cNombreCargo,
        e.fechaIngreso,
        c.nSueldo,
        e.bactivo
    FROM Empleado e
    INNER JOIN Persona p ON e.nPersonaId = p.nPersonaId
    INNER JOIN Cargo c ON e.nCargoId = c.nCargoId
    --WHERE e.bactivo = 1
END
