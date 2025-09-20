-- 6. SP para obtener usuarios activos
CREATE PROCEDURE sp_obtener_usuarios_movimientos
AS
BEGIN
    SET NOCOUNT ON;

    -- Usuarios únicos de movimientos de inventario y caja
    SELECT DISTINCT
        ISNULL(cCodUsuario, 'Sistema') as Usuario
    FROM MovimientoInventario with(nolock)
    WHERE cCodUsuario IS NOT NULL AND cCodUsuario != ''

    UNION

    SELECT DISTINCT
        cUsuario as Usuario
    FROM MovimientoCaja  with(nolock)
    WHERE cUsuario IS NOT NULL AND cUsuario != ''

    ORDER BY Usuario;
END;