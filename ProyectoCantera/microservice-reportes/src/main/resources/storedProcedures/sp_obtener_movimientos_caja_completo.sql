-- 2. SP para obtener movimientos de caja con filtros
CREATE PROCEDURE sp_obtener_movimientos_caja_completo
(
    @FechaDesde DATE = NULL,
    @FechaHasta DATE = NULL,
    @TipoMovimientoId INT = NULL,
    @Usuario VARCHAR(100) = NULL,
    @SesionId INT = NULL,
    @TipoPago VARCHAR(50) = NULL,
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
    FROM MovimientoCaja mc with(nolock)
    INNER JOIN SesionCaja sc with(nolock) ON mc.nSesionCajaId = sc.nSesionCajaId
    INNER JOIN TipoMovimientoCaja tmc with(nolock) ON mc.nTipoMovimientoCajaId = tmc.nTipoMovimientoCajaId
    LEFT JOIN Pedidos p with(nolock) ON mc.nPedidoID = p.PedidoID
    LEFT JOIN Comprobantes c with(nolock) ON mc.nComprobanteID = c.ComprobanteID
    WHERE CAST(mc.dFechaMovimiento AS DATE) BETWEEN @FechaDesde AND @FechaHasta
        AND (@TipoMovimientoId IS NULL OR mc.nTipoMovimientoCajaId = @TipoMovimientoId)
        AND (@Usuario IS NULL OR mc.cUsuario = @Usuario)
        AND (@SesionId IS NULL OR mc.nSesionCajaId = @SesionId)
        AND (@TipoPago IS NULL OR mc.cTipoPago = @TipoPago);

    -- Obtener datos paginados
    SELECT
        mc.nMovimientoCajaId as MovimientoId,
        mc.dFechaMovimiento as FechaMovimiento,
        sc.nSesionCajaId as SesionId,
        sc.cUsuario as UsuarioSesion,
        tmc.cDescripcion as TipoMovimiento,
        tmc.bEsIngreso as EsIngreso,
        mc.cDescripcion as Descripcion,
        mc.nMonto as Monto,
        mc.cTipoPago as TipoPago,
        mc.nPedidoID as PedidoId,
        mc.nComprobanteID as ComprobanteId,
        mc.cUsuario as Usuario,
        mc.cObservaciones as Observaciones,
        p.Mesa as MesaPedido,
        c.NumeroComprobante as NumeroComprobante,
        CASE
            WHEN tmc.bEsIngreso = 1 THEN 'INGRESO'
            ELSE 'EGRESO'
        END as TipoOperacion,
        @TotalRegistros as TotalRegistros
    FROM MovimientoCaja mc with(nolock)
    INNER JOIN SesionCaja sc with(nolock) ON mc.nSesionCajaId = sc.nSesionCajaId
    INNER JOIN TipoMovimientoCaja tmc with(nolock) ON mc.nTipoMovimientoCajaId = tmc.nTipoMovimientoCajaId
    LEFT JOIN Pedidos p with(nolock) ON mc.nPedidoID = p.PedidoID
    LEFT JOIN Comprobantes c with(nolock) ON mc.nComprobanteID = c.ComprobanteID
    WHERE CAST(mc.dFechaMovimiento AS DATE) BETWEEN @FechaDesde AND @FechaHasta
        AND (@TipoMovimientoId IS NULL OR mc.nTipoMovimientoCajaId = @TipoMovimientoId)
        AND (@Usuario IS NULL OR mc.cUsuario = @Usuario)
        AND (@SesionId IS NULL OR mc.nSesionCajaId = @SesionId)
        AND (@TipoPago IS NULL OR mc.cTipoPago = @TipoPago)
    ORDER BY mc.dFechaMovimiento DESC
    OFFSET @Offset ROWS
    FETCH NEXT @PageSize ROWS ONLY;
END;
