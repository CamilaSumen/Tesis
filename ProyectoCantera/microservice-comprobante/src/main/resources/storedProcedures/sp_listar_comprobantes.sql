CREATE PROCEDURE sp_listar_comprobantes
(
    @FechaInicio DATE = NULL,
    @FechaFin DATE = NULL,
    @Mozo VARCHAR(100) = NULL
)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        c.ComprobanteID,
        c.PedidoID,
        c.NumeroComprobante,
        c.TipoComprobante,
        c.Mesa,
        c.Mozo,
        c.FechaPago,
        c.TipoPago,
        c.TotalFinal,
        c.Estado,
        ISNULL(cl.Nombre + ' ' + cl.Apellido, 'Cliente General') as NombreCliente
    FROM Comprobantes c
    LEFT JOIN Clientes cl ON c.ClienteID = cl.ClienteID
    WHERE
        (@FechaInicio IS NULL OR CAST(c.FechaPago AS DATE) >= @FechaInicio) AND
        (@FechaFin IS NULL OR CAST(c.FechaPago AS DATE) <= @FechaFin) AND
        (@Mozo IS NULL OR c.Mozo = @Mozo)
    ORDER BY c.FechaPago DESC;
END;