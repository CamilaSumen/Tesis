package microservice_reportes.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VentasDelDia implements Serializable {
    private static final long serialVersionUID = 1L;

    private BigDecimal totalVentas;
    private Integer cantidadPedidos;
    private BigDecimal promedioTicket;
}
