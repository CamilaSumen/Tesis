package microservice_cocina.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CambioEstadoRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer pedidoID;
    private String nuevoEstado;
}