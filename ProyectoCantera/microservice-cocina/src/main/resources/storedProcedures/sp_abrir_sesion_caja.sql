-- =============================================
-- SP: Abrir sesión de caja
-- =============================================
CREATE PROCEDURE sp_abrir_sesion_caja
    @Usuario VARCHAR(100),
    @MontoApertura DECIMAL(10,2),
    @Observaciones VARCHAR(500) = NULL
AS
BEGIN
    SET NOCOUNT ON;

    -- Verificar que no hay sesión abierta para el usuario
    IF EXISTS (SELECT 1 FROM SesionCaja WHERE cUsuario = @Usuario AND cEstado = 'Abierta')
    BEGIN
        SELECT 0 as Success, 'Ya existe una sesión de caja abierta para este usuario' as Mensaje;
        RETURN;
    END

    DECLARE @NuevaSesionId INT;
    DECLARE @TipoApertura INT;

    -- Crear nueva sesión
    INSERT INTO SesionCaja (cUsuario, nMontoApertura, cObservaciones)
    VALUES (@Usuario, @MontoApertura, @Observaciones);

    SET @NuevaSesionId = SCOPE_IDENTITY();

    -- Obtener tipo de movimiento para apertura
    SELECT @TipoApertura = nTipoMovimientoCajaId
    FROM TipoMovimientoCaja
    WHERE cDescripcion = 'Apertura de Caja' AND bEstado = 1;

    -- Registrar movimiento de apertura
    IF @TipoApertura IS NOT NULL AND @MontoApertura > 0
    BEGIN
        INSERT INTO MovimientoCaja (
            nSesionCajaId, nTipoMovimientoCajaId, cDescripcion,
            nMonto, cTipoPago, cUsuario, cObservaciones
        ) VALUES (
            @NuevaSesionId, @TipoApertura, 'Apertura de caja',
            @MontoApertura, 'Efectivo', @Usuario, 'Dinero inicial en caja'
        );
    END

    SELECT 1 as Success, 'Sesión de caja abierta correctamente' as Mensaje, @NuevaSesionId as SesionId;
END
