IF OBJECT_ID('PA_Cargo_Sel_ListarConEmpleados') IS NOT NULL
    DROP PROCEDURE PA_Cargo_Sel_ListarConEmpleados
GO
/*---------------------------------------------------------------------------------
PROPÓSITO			| Lista cargos con la cantidad de empleados activos
AUTOR				| Jorge Bonifaz
FECHA DE CREACIÓN	| 2025-12-19
-----------------------------------------------------------------------------------
EJEMPLO:
EXEC PA_Cargo_Sel_ListarConEmpleados
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Cargo_Sel_ListarConEmpleados
AS
BEGIN
    SET NOCOUNT ON

    SELECT
        c.nCargoId,
        c.cNombreCargo,
        c.cDescripcion,
        c.nSueldo,
        c.bEstado,
        COUNT(e.nEmpleadoId) AS nCantidadEmpleados
    FROM Cargo c WITH(NOLOCK)
    LEFT JOIN Empleado e WITH(NOLOCK)
        ON c.nCargoId = e.nCargoId
        AND e.bactivo = 1  -- Solo empleados activos
    GROUP BY
        c.nCargoId,
        c.cNombreCargo,
        c.cDescripcion,
        c.nSueldo,
        c.bEstado
    ORDER BY c.cNombreCargo
END
GO