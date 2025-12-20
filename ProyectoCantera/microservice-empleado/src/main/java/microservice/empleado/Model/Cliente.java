package microservice.empleado.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;


}
