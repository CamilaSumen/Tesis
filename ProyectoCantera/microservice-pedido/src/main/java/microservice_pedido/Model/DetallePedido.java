package microservice_pedido.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetallePedido implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer detalleID;
    private Integer pedidoID;
    private Integer productoID;
    private String nombreProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
}