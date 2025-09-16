package microservice_comprobante.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComprobanteResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer comprobanteID;
    private String numeroComprobante;
    private BigDecimal totalFinal;
    private String mensaje;
}