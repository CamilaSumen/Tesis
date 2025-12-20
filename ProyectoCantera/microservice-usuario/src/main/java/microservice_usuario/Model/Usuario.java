package microservice_usuario.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;
    private String username;
    private String password;
    private String cargoCod;
    private Integer cargoId;

    private Integer privilegeId;
    private String privilegeName;
    private Integer shiftId;
    private String shiftName;
    private Integer employeeId;
    private String firstName;
    private String lastNameFather;
    private String lastNameMother;
    private String dni;
    private String email;
    private String phone;
    private Boolean status;
}
