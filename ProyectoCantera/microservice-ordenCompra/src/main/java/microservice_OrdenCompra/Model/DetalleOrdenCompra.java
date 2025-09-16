package microservice_OrdenCompra.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetalleOrdenCompra implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer detalleId;
    private Integer ordenCompraId;
    private Integer insumoId;
    private String nombreInsumo;
    private String unidadMedida;
    private String categoria;
    private BigDecimal cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
}
