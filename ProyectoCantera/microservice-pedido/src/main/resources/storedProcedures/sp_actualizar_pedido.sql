-- Procedimiento para actualizar pedido completo
CREATE PROCEDURE sp_actualizar_pedido
(
    @PedidoID INT,
    @Mesa VARCHAR(10),
    @Mozo VARCHAR(100),
    @NumeroPersonas INT,
    @Observaciones TEXT = NULL,
    @DetalleJSON NVARCHAR(MAX)
)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @Total DECIMAL(10,2) = 0;

    BEGIN TRY
        BEGIN TRANSACTION;

        -- Actualizar la cabecera del pedido
        UPDATE Pedidos
        SET
            Mesa = @Mesa,
            Mozo = @Mozo,
            NumeroPersonas = @NumeroPersonas,
            Observaciones = @Observaciones,
            FechaActualizacion = GETDATE()
        WHERE PedidoID = @PedidoID;

        -- Eliminar el detalle anterior
        DELETE FROM DetallePedido WHERE PedidoID = @PedidoID;

        -- Insertar el nuevo detalle desde JSON
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
            CAST(JSON_VALUE(value, '$.productoID') AS INT),
            JSON_VALUE(value, '$.nombreProducto'),
            CAST(JSON_VALUE(value, '$.cantidad') AS INT),
            CAST(JSON_VALUE(value, '$.precioUnitario') AS DECIMAL(10,2)),
            CAST(JSON_VALUE(value, '$.cantidad') AS INT) * CAST(JSON_VALUE(value, '$.precioUnitario') AS DECIMAL(10,2))
        FROM OPENJSON(@DetalleJSON)
        WHERE JSON_VALUE(value, '$.productoID') IS NOT NULL
          AND JSON_VALUE(value, '$.nombreProducto') IS NOT NULL
          AND JSON_VALUE(value, '$.cantidad') IS NOT NULL
          AND JSON_VALUE(value, '$.precioUnitario') IS NOT NULL;

        -- Calcular el nuevo total
        SELECT @Total = SUM(Subtotal)
        FROM DetallePedido
        WHERE PedidoID = @PedidoID;

        -- Actualizar el total en la cabecera
        UPDATE Pedidos
        SET Total = @Total
        WHERE PedidoID = @PedidoID;

        COMMIT TRANSACTION;

        -- Retornar confirmación
        SELECT
            @PedidoID AS PedidoID,
            @Total AS Total,
            'Pedido actualizado exitosamente' AS Mensaje;

    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;

        -- Retornar información del error
        SELECT
            ERROR_NUMBER() AS ErrorNumber,
            ERROR_MESSAGE() AS ErrorMessage,
            'Error al actualizar el pedido' AS Mensaje;
    END CATCH
END;