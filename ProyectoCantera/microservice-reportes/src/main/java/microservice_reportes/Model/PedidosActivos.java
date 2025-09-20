package microservice_reportes.Model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidosActivos implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer enCocina;
    private Integer pendientes;
    private Integer completados;
    private Integer total;
}