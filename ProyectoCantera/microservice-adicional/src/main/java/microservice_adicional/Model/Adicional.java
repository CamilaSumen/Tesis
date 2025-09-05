package microservice_adicional.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Adicional implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer adicionalId;
    private String adicionalName;
    private String adicionalDescription;
    private Double adicionalPrecio;
    private LocalTime adicionalfechaRegistro;
    private Boolean stateAdicional;
}
