package microservice_OrdenCompra.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdenCompraResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer ordenCompraId;
    private BigDecimal total;
    private String mensaje;
    private Boolean success;
}