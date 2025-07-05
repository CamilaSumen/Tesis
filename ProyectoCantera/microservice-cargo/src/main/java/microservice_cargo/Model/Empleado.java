package microservice_cargo.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idEmployer;
    private String firstName;
    private String lastNameFather;
    private String lastNameMother;
    private LocalDate birthDate;
    private String address;
    private String phone;
    private String dni;
    private String email;

    private Integer chargeId;
    private LocalDate entryDate;
    private Double salary;
    private boolean stateEmployer;
}
