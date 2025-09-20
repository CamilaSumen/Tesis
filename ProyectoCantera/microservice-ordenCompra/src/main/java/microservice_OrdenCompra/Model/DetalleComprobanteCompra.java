package microservice_OrdenCompra.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetalleComprobanteCompra {
    private Integer detalleId;
    private Integer comprobanteCompraId;
    private Integer insumoId;
    private String nombreInsumo;
    private BigDecimal cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
}