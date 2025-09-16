-- =============================================
-- SP: Registrar venta en caja cuando se cobra un pedido
-- =============================================
CREATE PROCEDURE sp_registrar_venta_caja
    @PedidoID INT,
    @ComprobanteID INT = NULL,
    @TipoPago VARCHAR(50),
    @MontoTotal DECIMAL(10,2),
    @MontoPropina DECIMAL(10,2) = 0,
    @MontoDescuento DECIMAL(10,2) = 0,
    @Usuario VARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @SesionActiva INT;
    DECLARE @TipoMovimientoVenta INT;
    DECLARE @TipoMovimientoPropina INT;
    DECLARE @TipoMovimientoDescuento INT;

    -- Verificar que existe una sesión activa para el usuario
    SELECT @SesionActiva = nSesionCajaId
    FROM SesionCaja
    WHERE cUsuario = @Usuario AND cEstado = 'Abierta'
    ORDER BY dFechaApertura DESC;

    IF @SesionActiva IS NULL
    BEGIN
        SELECT 0 as Success, 'No hay sesión de caja abierta para el usuario' as Mensaje;
        RETURN;
    END

    -- Obtener tipo de movimiento según método de pago
    SELECT @TipoMovimientoVenta = nTipoMovimientoCajaId
    FROM TipoMovimientoCaja
    WHERE cDescripcion = 'Venta - ' + @TipoPago AND bEstado = 1;

    IF @TipoMovimientoVenta IS NULL
    BEGIN
        -- Si no existe el tipo específico, usar "Venta - Efectivo" como default
        SELECT @TipoMovimientoVenta = nTipoMovimientoCajaId
        FROM TipoMovimientoCaja
        WHERE cDescripcion = 'Venta - Efectivo' AND bEstado = 1;
    END

    -- Obtener tipos para propina y descuento
    SELECT @TipoMovimientoPropina = nTipoMovimientoCajaId
    FROM TipoMovimientoCaja
    WHERE cDescripcion = 'Propinas' AND bEstado = 1;

    SELECT @TipoMovimientoDescuento = nTipoMovimientoCajaId
    FROM TipoMovimientoCaja
    WHERE cDescripcion = 'Descuentos' AND bEstado = 1;

    BEGIN TRANSACTION;

    BEGIN TRY
        -- Registrar venta principal
        INSERT INTO MovimientoCaja (
            nSesionCajaId, nTipoMovimientoCajaId, nPedidoID, nComprobanteID,
            cDescripcion, nMonto, cTipoPago, cUsuario, cObservaciones
        ) VALUES (
            @SesionActiva, @TipoMovimientoVenta, @PedidoID, @ComprobanteID,
            'Venta Pedido #' + CAST(@PedidoID AS VARCHAR(10)),
            @MontoTotal, @TipoPago, @Usuario,
            'Venta registrada - Método: ' + @TipoPago
        );

        -- Registrar propina si existe
        IF @MontoPropina > 0 AND @TipoMovimientoPropina IS NOT NULL
        BEGIN
            INSERT INTO MovimientoCaja (
                nSesionCajaId, nTipoMovimientoCajaId, nPedidoID, nComprobanteID,
                cDescripcion, nMonto, cTipoPago, cUsuario, cObservaciones
            ) VALUES (
                @SesionActiva, @TipoMovimientoPropina, @PedidoID, @ComprobanteID,
                'Propina Pedido #' + CAST(@PedidoID AS VARCHAR(10)),
                @MontoPropina, @TipoPago, @Usuario,
                'Propina recibida'
            );
        END

        -- Registrar descuento si existe
        IF @MontoDescuento > 0 AND @TipoMovimientoDescuento IS NOT NULL
        BEGIN
            INSERT INTO MovimientoCaja (
                nSesionCajaId, nTipoMovimientoCajaId, nPedidoID, nComprobanteID,
                cDescripcion, nMonto, cTipoPago, cUsuario, cObservaciones
            ) VALUES (
                @SesionActiva, @TipoMovimientoDescuento, @PedidoID, @ComprobanteID,
                'Descuento Pedido #' + CAST(@PedidoID AS VARCHAR(10)),
                @MontoDescuento, @TipoPago, @Usuario,
                'Descuento aplicado'
            );
        END

        COMMIT TRANSACTION;

        SELECT 1 as Success, 'Venta registrada en caja correctamente' as Mensaje;

    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        SELECT 0 as Success, ERROR_MESSAGE() as Mensaje;
    END CATCH
END