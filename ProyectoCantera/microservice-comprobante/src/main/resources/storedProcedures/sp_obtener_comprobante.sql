CREATE PROCEDURE sp_obtener_comprobante
(
    @ComprobanteID INT
)
AS
BEGIN
    SET NOCOUNT ON;

    -- Obtener datos del comprobante con cliente
    SELECT
        c.ComprobanteID,
        c.PedidoID,
        c.TipoComprobante,
        c.NumeroComprobante,
        c.Mesa,
        c.Mozo,
        c.FechaPago,
        c.TipoPago,
        c.Subtotal,
        c.Descuento,
        c.Propina,
        c.TotalFinal,
        c.MontoRecibido,
        c.Vuelto,
        c.Estado,

        -- Datos del cliente
        cl.DNI as ClienteDNI,
        cl.Nombre as ClienteNombre,
        cl.Apellido as ClienteApellido,
        cl.Email as ClienteEmail,
        cl.Telefono as ClienteTelefono

    FROM Comprobantes c
    LEFT JOIN Clientes cl ON c.ClienteID = cl.ClienteID
    WHERE c.ComprobanteID = @ComprobanteID;

    -- Obtener detalle del comprobante
    SELECT
        dc.ProductoID,
        dc.NombreProducto,
        dc.Cantidad,
        dc.PrecioUnitario,
        dc.Subtotal
    FROM DetalleComprobante dc
    WHERE dc.ComprobanteID = @ComprobanteID
    ORDER BY dc.DetalleComprobanteID;
END;