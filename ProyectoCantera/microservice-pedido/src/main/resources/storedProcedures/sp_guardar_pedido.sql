IF OBJECT_ID('sp_guardar_pedido') IS NOT NULL
    DROP PROCEDURE sp_guardar_pedido
GO
-- Procedimiento almacenado para guardar pedido con detalle
CREATE PROCEDURE sp_guardar_pedido
(
    @Mesa VARCHAR(10),
    @Mozo VARCHAR(100),
    @NumeroPersonas INT,
    @Observaciones VARCHAR(200),
    @DetalleJSON VARCHAR(MAX) -- JSON con los productos del pedido
)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @PedidoID INT;
    DECLARE @Total DECIMAL(10,2) = 0;

    BEGIN TRY
        BEGIN TRANSACTION;

        -- Insertar la cabecera del pedido
        INSERT INTO Pedidos (
            Mesa,
            Mozo,
            NumeroPersonas,
            FechaPedido,
            Estado,
            Observaciones,
            Total
        )
        VALUES (
            @Mesa,
            @Mozo,
            @NumeroPersonas,
            GETDATE(),
            'Pendiente',
            @Observaciones,
            0 -- Se actualizará después
        );

        -- Obtener el ID del pedido insertado
        SET @PedidoID = SCOPE_IDENTITY();

        -- Insertar el detalle del pedido desde JSON
        INSERT INTO DetallePedido (
            PedidoID,
            ProductoID,
            NombreProducto,
            Cantidad,
            PrecioUnitario,
            Subtotal
        )
        SELECT
            @PedidoID,
            JSON_VALUE(value, '$.ProductoID'),
            JSON_VALUE(value, '$.NombreProducto'),
            CAST(JSON_VALUE(value, '$.Cantidad') AS INT),
            CAST(JSON_VALUE(value, '$.PrecioUnitario') AS DECIMAL(10,2)),
            CAST(JSON_VALUE(value, '$.Cantidad') AS INT) * CAST(JSON_VALUE(value, '$.PrecioUnitario') AS DECIMAL(10,2))
        FROM OPENJSON(@DetalleJSON);

        -- Calcular el total del pedido
        SELECT @Total = SUM(Subtotal)
        FROM DetallePedido
        WHERE PedidoID = @PedidoID;

        -- Actualizar el total en la cabecera del pedido
        UPDATE Pedidos
        SET Total = @Total
        WHERE PedidoID = @PedidoID;

        COMMIT TRANSACTION;

        -- Retornar el ID del pedido creado y el total
        SELECT
            @PedidoID AS PedidoID,
            @Total AS Total,
            'Pedido guardado exitosamente' AS Mensaje;

    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;

        -- Retornar información del error
        SELECT
            ERROR_NUMBER() AS ErrorNumber,
            ERROR_MESSAGE() AS ErrorMessage,
            'Error al guardar el pedido' AS Mensaje;
    END CATCH
END;

GO

-- Ejemplo de uso del procedimiento almacenado
/*
DECLARE @DetalleJSON NVARCHAR(MAX) = '[
    {
        "ProductoID": 1,
        "NombreProducto": "producto 1",
        "Cantidad": 3,
        "PrecioUnitario": 60.00
    },
    {
        "ProductoID": 2,
        "NombreProducto": "Producto 2",
        "Cantidad": 1,
        "PrecioUnitario": 30.00
    },
    {
        "ProductoID": 3,
        "NombreProducto": "Producto 3",
        "Cantidad": 1,
        "PrecioUnitario": 60.00
    }
]';

EXEC sp_guardar_pedido
    @Mesa = '03',
    @Mozo = 'JABA',
    @NumeroPersonas = 1,
    @Observaciones = 'Sin cebolla en el producto 4',
    @DetalleJSON = @DetalleJSON;
*/