package microservice_comprobante.Model;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer clienteID;
    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private LocalDateTime fechaRegistro;
    private Boolean activo;
}
