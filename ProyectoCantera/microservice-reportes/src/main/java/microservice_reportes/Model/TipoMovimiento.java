package microservice_reportes.Model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TipoMovimiento implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tipoSistema;
    private Integer tipoId;
    private String descripcion;
    private Boolean activo;
}