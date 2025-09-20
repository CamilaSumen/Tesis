CREATE PROCEDURE sp_obtener_movimientos_consolidados
(
    @FechaDesde DATE = NULL,
    @FechaHasta DATE = NULL,
    @Usuario VARCHAR(100) = NULL,
    @TipoMovimiento VARCHAR(20) = NULL, -- 'INVENTARIO', 'CAJA', NULL (ambos)
    @PageNumber INT = 1,
    @PageSize INT = 50
)
AS
BEGIN
    SET NOCOUNT ON;

    -- Si no se especifican fechas, usar los últimos 7 días
    IF @FechaDesde IS NULL SET @FechaDesde = DATEADD(DAY, -7, GETDATE());
    IF @FechaHasta IS NULL SET @FechaHasta = GETDATE();

    DECLARE @Offset INT = (@PageNumber - 1) * @PageSize;

    -- Crear tabla temporal para consolidar movimientos
    CREATE TABLE #MovimientosConsolidados (
        Id INT IDENTITY(1,1),
        TipoSistema VARCHAR(20),
        FechaMovimiento DATETIME,
        Usuario VARCHAR(100),
        Descripcion VARCHAR(500),
        Detalle VARCHAR(1000),
        MontoAfectado DECIMAL(10,2),
        TipoOperacion VARCHAR(20),
        Referencia VARCHAR(100)
    );

    -- Insertar movimientos de inventario
    IF @TipoMovimiento IS NULL OR @TipoMovimiento = 'INVENTARIO'
    BEGIN
        INSERT INTO #MovimientosConsolidados
        SELECT
            'INVENTARIO' as TipoSistema,
            mi.dFechaMovimiento as FechaMovimiento,
            ISNULL(mi.cCodUsuario, 'Sistema') as Usuario,
            tmi.cDescripcion as Descripcion,
            CONCAT(
                i.cNombreInsumo, ' - ',
                CASE WHEN mi.nCantidadMovimiento > 0 THEN '+' ELSE '' END,
                CAST(mi.nCantidadMovimiento AS VARCHAR(20)), ' ', i.cUnidadMedida,
                ' (Stock: ', CAST(mi.nStockAnterior AS VARCHAR(20)), ' → ', CAST(mi.nStockNuevo AS VARCHAR(20)), ')'
            ) as Detalle,
            0 as MontoAfectado, -- Los movimientos de inventario no tienen monto directo
            CASE WHEN mi.nCantidadMovimiento > 0 THEN 'ENTRADA' ELSE 'SALIDA' END as TipoOperacion,
            CASE
                WHEN mi.nPedidoID IS NOT NULL THEN CONCAT('Pedido #', mi.nPedidoID)
                ELSE 'Sistema'
            END as Referencia
        FROM MovimientoInventario mi WITH(NOLOCK)
        INNER JOIN TipoMovimientoInventario tmi WITH(NOLOCK) ON mi.nTipoMovimientoInventarioId = tmi.nTipoMovimientoInventarioId
        INNER JOIN Insumo i WITH(NOLOCK) ON mi.nInsumoId = i.nInsumoId
        WHERE CAST(mi.dFechaMovimiento AS DATE) BETWEEN @FechaDesde AND @FechaHasta
            AND (@Usuario IS NULL OR mi.cCodUsuario = @Usuario);
    END

    -- Insertar movimientos de caja
    IF @TipoMovimiento IS NULL OR @TipoMovimiento = 'CAJA'
    BEGIN
        INSERT INTO #MovimientosConsolidados
        SELECT
            'CAJA' as TipoSistema,
            mc.dFechaMovimiento as FechaMovimiento,
            mc.cUsuario as Usuario,
            tmc.cDescripcion as Descripcion,
            CONCAT(
                mc.cDescripcion,
                CASE WHEN mc.cTipoPago IS NOT NULL THEN CONCAT(' (', mc.cTipoPago, ')') ELSE '' END,
                CASE WHEN mc.cObservaciones IS NOT NULL THEN CONCAT(' - ', mc.cObservaciones) ELSE '' END
            ) as Detalle,
            mc.nMonto as MontoAfectado,
            CASE WHEN tmc.bEsIngreso = 1 THEN 'INGRESO' ELSE 'EGRESO' END as TipoOperacion,
            CASE
                WHEN mc.nPedidoID IS NOT NULL THEN CONCAT('Pedido #', mc.nPedidoID)
                WHEN mc.nComprobanteID IS NOT NULL THEN CONCAT('Comprobante #', mc.nComprobanteID)
                ELSE 'Sesión #' + CAST(mc.nSesionCajaId AS VARCHAR(10))
            END as Referencia
        FROM MovimientoCaja mc WITH(NOLOCK)
        INNER JOIN TipoMovimientoCaja tmc WITH(NOLOCK) ON mc.nTipoMovimientoCajaId = tmc.nTipoMovimientoCajaId
        WHERE CAST(mc.dFechaMovimiento AS DATE) BETWEEN @FechaDesde AND @FechaHasta
            AND (@Usuario IS NULL OR mc.cUsuario = @Usuario);
    END

    -- Contar total de registros
    DECLARE @TotalRegistros INT;
    SELECT @TotalRegistros = COUNT(*) FROM #MovimientosConsolidados;

    -- Retornar resultados paginados
    SELECT
        TipoSistema,
        FechaMovimiento,
        Usuario,
        Descripcion,
        Detalle,
        MontoAfectado,
        TipoOperacion,
        Referencia,
        @TotalRegistros as TotalRegistros
    FROM #MovimientosConsolidados
    ORDER BY FechaMovimiento DESC
    OFFSET @Offset ROWS
    FETCH NEXT @PageSize ROWS ONLY;

    -- Limpiar tabla temporal
    DROP TABLE #MovimientosConsolidados;
END;