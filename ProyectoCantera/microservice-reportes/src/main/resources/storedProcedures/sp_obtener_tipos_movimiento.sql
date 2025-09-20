-- 5. SP para obtener tipos de movimiento disponibles
CREATE PROCEDURE sp_obtener_tipos_movimiento
AS
BEGIN
    SET NOCOUNT ON;

    -- Tipos de movimiento de inventario
    SELECT
        'INVENTARIO' as TipoSistema,
        nTipoMovimientoInventarioId as TipoId,
        cDescripcion as Descripcion,
        bEstado as Activo
    FROM TipoMovimientoInventario WITH(NOLOCK)
    WHERE bEstado = 1

    UNION ALL

    -- Tipos de movimiento de caja
    SELECT
        'CAJA' as TipoSistema,
        nTipoMovimientoCajaId as TipoId,
        cDescripcion as Descripcion,
        bEstado as Activo
    FROM TipoMovimientoCaja WITH(NOLOCK)
    WHERE bEstado = 1

    ORDER BY TipoSistema, Descripcion;
END;
