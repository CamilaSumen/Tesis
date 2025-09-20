-- 1. SP para obtener movimientos de inventario con filtros
CREATE PROCEDURE sp_obtener_movimientos_inventario_completo
(
    @FechaDesde DATE = NULL,
    @FechaHasta DATE = NULL,
    @InsumoId INT = NULL,
    @TipoMovimientoId INT = NULL,
    @Usuario VARCHAR(100) = NULL,
    @PageNumber INT = 1,
    @PageSize INT = 50
)
AS
BEGIN
    SET NOCOUNT ON;

    -- Si no se especifican fechas, usar los últimos 30 días
    IF @FechaDesde IS NULL SET @FechaDesde = DATEADD(DAY, -30, GETDATE());
    IF @FechaHasta IS NULL SET @FechaHasta = GETDATE();

    DECLARE @Offset INT = (@PageNumber - 1) * @PageSize;

    -- Contar total de registros
    DECLARE @TotalRegistros INT;
    SELECT @TotalRegistros = COUNT(*)
    FROM MovimientoInventario mi WITH(NOLOCK)
    INNER JOIN TipoMovimientoInventario tmi WITH(NOLOCK) ON mi.nTipoMovimientoInventarioId = tmi.nTipoMovimientoInventarioId
    INNER JOIN Insumo i WITH(NOLOCK) ON mi.nInsumoId = i.nInsumoId
    INNER JOIN CategoriaInsumo ci WITH(NOLOCK) ON i.nCategoriaInsumoId = ci.nCategoriaInsumoId
    LEFT JOIN Pedidos p WITH(NOLOCK) ON mi.nPedidoID = p.PedidoID
    WHERE CAST(mi.dFechaMovimiento AS DATE) BETWEEN @FechaDesde AND @FechaHasta
        AND (@InsumoId IS NULL OR mi.nInsumoId = @InsumoId)
        AND (@TipoMovimientoId IS NULL OR mi.nTipoMovimientoInventarioId = @TipoMovimientoId)
        AND (@Usuario IS NULL OR mi.cCodUsuario = @Usuario);

    -- Obtener datos paginados
    SELECT
        mi.nMovimientoInventarioId as MovimientoId,
        mi.dFechaMovimiento as FechaMovimiento,
        tmi.cDescripcion as TipoMovimiento,
        i.nInsumoId as InsumoId,
        i.cNombreInsumo as NombreInsumo,
        i.cUnidadMedida as UnidadMedida,
        ci.cNombre as CategoriaInsumo,
        mi.nCantidadMovimiento as CantidadMovimiento,
        mi.nStockAnterior as StockAnterior,
        mi.nStockNuevo as StockNuevo,
        mi.cObservaciones as Observaciones,
        mi.nPedidoID as PedidoId,
        mi.cCodUsuario as Usuario,
        p.Mesa as MesaPedido,
        CASE
            WHEN mi.nCantidadMovimiento > 0 THEN 'ENTRADA'
            ELSE 'SALIDA'
        END as TipoOperacion,
        @TotalRegistros as TotalRegistros
    FROM MovimientoInventario mi WITH(NOLOCK)
    INNER JOIN TipoMovimientoInventario tmi WITH(NOLOCK) ON mi.nTipoMovimientoInventarioId = tmi.nTipoMovimientoInventarioId
    INNER JOIN Insumo i WITH(NOLOCK) ON mi.nInsumoId = i.nInsumoId
    INNER JOIN CategoriaInsumo ci WITH(NOLOCK) ON i.nCategoriaInsumoId = ci.nCategoriaInsumoId
    LEFT JOIN Pedidos p WITH(NOLOCK) ON mi.nPedidoID = p.PedidoID
    WHERE CAST(mi.dFechaMovimiento AS DATE) BETWEEN @FechaDesde AND @FechaHasta
        AND (@InsumoId IS NULL OR mi.nInsumoId = @InsumoId)
        AND (@TipoMovimientoId IS NULL OR mi.nTipoMovimientoInventarioId = @TipoMovimientoId)
        AND (@Usuario IS NULL OR mi.cCodUsuario = @Usuario)
    ORDER BY mi.dFechaMovimiento DESC
    OFFSET @Offset ROWS
    FETCH NEXT @PageSize ROWS ONLY;
END;