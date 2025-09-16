-- 2. Procedimiento para guardar comprobante
CREATE PROCEDURE sp_guardar_comprobante
(
    @PedidoID INT,
    @ClienteDNI VARCHAR(20) = NULL,
    @ClienteNombre VARCHAR(100) = NULL,
    @ClienteApellido VARCHAR(100) = NULL,
    @TipoComprobante VARCHAR(20) = 'BOLETA',
    @Mesa VARCHAR(10),
    @Mozo VARCHAR(100),
    @TipoPago VARCHAR(50),
    @Subtotal DECIMAL(10,2),
    @Descuento DECIMAL(10,2) = 0,
    @Propina DECIMAL(10,2) = 0,
    @TotalFinal DECIMAL(10,2),
    @MontoRecibido DECIMAL(10,2) = NULL,
    @Vuelto DECIMAL(10,2) = 0
)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @ComprobanteID INT;
    DECLARE @ClienteID INT = NULL;
    DECLARE @NumeroComprobante VARCHAR(50);

    BEGIN TRY
        BEGIN TRANSACTION;

        -- Registrar cliente si se proporcionó DNI
        IF @ClienteDNI IS NOT NULL AND @ClienteNombre IS NOT NULL
        BEGIN
            EXEC sp_registrar_cliente
                @DNI = @ClienteDNI,
                @Nombre = @ClienteNombre,
                @Apellido = @ClienteApellido;

            SELECT @ClienteID = ClienteID
            FROM Clientes
            WHERE DNI = @ClienteDNI AND Activo = 1;
        END

        -- Generar número de comprobante
        DECLARE @Correlativo INT;
        SELECT @Correlativo = ISNULL(MAX(ComprobanteID), 0) + 1 FROM Comprobantes;

        IF @TipoComprobante = 'BOLETA'
            SET @NumeroComprobante = 'B001-' + FORMAT(@Correlativo, '00000000');
        ELSE
            SET @NumeroComprobante = 'F001-' + FORMAT(@Correlativo, '00000000');

        -- Insertar comprobante
        INSERT INTO Comprobantes (
            PedidoID,
            ClienteID,
            TipoComprobante,
            NumeroComprobante,
            Mesa,
            Mozo,
            TipoPago,
            Subtotal,
            Descuento,
            Propina,
            TotalFinal,
            MontoRecibido,
            Vuelto
        )
        VALUES (
            @PedidoID,
            @ClienteID,
            @TipoComprobante,
            @NumeroComprobante,
            @Mesa,
            @Mozo,
            @TipoPago,
            @Subtotal,
            @Descuento,
            @Propina,
            @TotalFinal,
            @MontoRecibido,
            @Vuelto
        );

        SET @ComprobanteID = SCOPE_IDENTITY();

        -- Copiar detalle del pedido al comprobante
        INSERT INTO DetalleComprobante (
            ComprobanteID,
            ProductoID,
            NombreProducto,
            Cantidad,
            PrecioUnitario,
            Subtotal
        )
        SELECT
            @ComprobanteID,
            ProductoID,
            NombreProducto,
            Cantidad,
            PrecioUnitario,
            Subtotal
        FROM DetallePedido
        WHERE PedidoID = @PedidoID;

        -- Actualizar estado del pedido a PAGADO
        UPDATE Pedidos
        SET Estado = 'PAGADO'
        WHERE PedidoID = @PedidoID;

        COMMIT TRANSACTION;

        -- Retornar información del comprobante creado
        SELECT
            ComprobanteID,
            NumeroComprobante,
            TotalFinal,
            'Comprobante generado exitosamente' AS Mensaje
        FROM Comprobantes
        WHERE ComprobanteID = @ComprobanteID;

    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;

        SELECT
            ERROR_NUMBER() AS ErrorNumber,
            ERROR_MESSAGE() AS ErrorMessage,
            'Error al generar comprobante' AS Mensaje;
    END CATCH
END