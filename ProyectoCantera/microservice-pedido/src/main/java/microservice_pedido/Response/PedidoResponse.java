package microservice_pedido.Response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer pedidoID;
    private BigDecimal total;
    private String mensaje;
}