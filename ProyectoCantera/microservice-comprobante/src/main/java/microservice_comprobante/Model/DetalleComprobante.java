package microservice_comprobante.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetalleComprobante implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer detalleComprobanteID;
    private Integer comprobanteID;
    private Integer productoID;
    private String nombreProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
}