-- =============================================
-- SP: Registrar pago de orden de compra en caja
-- =============================================
ALTER PROCEDURE sp_registrar_pago_orden_compra
(
    @OrdenCompraId INT,
    @TipoPago VARCHAR(50), -- Efectivo, Transferencia, Cheque
    @MontoPagado DECIMAL(10,2),
    @Usuario VARCHAR(100),
    @Observaciones VARCHAR(500) = NULL
)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @SesionActiva INT;
    DECLARE @TipoMovimientoPago INT;
    DECLARE @ProveedorNombre VARCHAR(100);
    DECLARE @TotalOrden DECIMAL(10,2);

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

    -- Obtener información de la orden
    SELECT @ProveedorNombre = p.nNombreProveedor, @TotalOrden = oc.nTotal
    FROM OrdenesCompra oc
    INNER JOIN Proveedores p ON oc.nProveedorId = p.nProveedorId
    WHERE oc.nOrdenesCompraId = @OrdenCompraId;

    IF @ProveedorNombre IS NULL
    BEGIN
        SELECT 0 as Success, 'Orden de compra no encontrada' as Mensaje;
        RETURN;
    END

    -- Obtener tipo de movimiento según método de pago
    SELECT @TipoMovimientoPago = nTipoMovimientoCajaId
    FROM TipoMovimientoCaja
    WHERE cDescripcion = 'Pago a Proveedor - ' + @TipoPago AND bEstado = 1;

    IF @TipoMovimientoPago IS NULL
    BEGIN
        -- Si no existe el tipo específico, usar "Pago a Proveedor - Efectivo" como default
        SELECT @TipoMovimientoPago = nTipoMovimientoCajaId
        FROM TipoMovimientoCaja
        WHERE cDescripcion = 'Pago a Proveedor - Efectivo' AND bEstado = 1;
    END

    BEGIN TRANSACTION;

    BEGIN TRY
        -- Registrar pago en caja
        INSERT INTO MovimientoCaja (
            nSesionCajaId, nTipoMovimientoCajaId,
            cDescripcion, nMonto, cTipoPago, cUsuario, cObservaciones
        ) VALUES (
            @SesionActiva, @TipoMovimientoPago,
            'Pago Orden Compra #' + CAST(@OrdenCompraId AS VARCHAR(10)) + ' - ' + @ProveedorNombre,
            @MontoPagado, @TipoPago, @Usuario,
            'Pago a proveedor - Método: ' + @TipoPago +
            ISNULL(' - ' + @Observaciones, '')
        );

		UPDATE OrdenesCompra
		SET bPagado = 1
		WHERE nOrdenesCompraId = @OrdenCompraId;

		COMMIT TRANSACTION;

        SELECT 1 as Success, 'Pago registrado en caja correctamente' as Mensaje;

    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        SELECT 0 as Success, ERROR_MESSAGE() as Mensaje;
    END CATCH
END;