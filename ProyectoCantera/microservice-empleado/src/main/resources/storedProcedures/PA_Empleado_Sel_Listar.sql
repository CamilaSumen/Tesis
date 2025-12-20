exec PA_Empleado_Sel_Listar

IF OBJECT_ID('PA_Empleado_Sel_Listar') IS NOT NULL
    DROP PROCEDURE PA_Empleado_Sel_Listar
GO
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
        p.cDireccion,        -- AGREGADO
        c.nCargoId,          -- AGREGADO
        c.cNombreCargo,
        c.nSueldo,
        e.fechaIngreso,
        p.cCorreo,
        p.cTelefono,
        p.dFechaNacimiento,
        e.bactivo
    FROM Empleado e with(nolock)
    INNER JOIN Persona p with(nolock) ON e.nPersonaId = p.nPersonaId
    INNER JOIN Cargo c with(nolock) ON e.nCargoId = c.nCargoId
    --WHERE e.bactivo = 1
END