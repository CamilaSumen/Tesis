package microservice_cargo.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Privilegio implements Serializable {

    private static final long serialVersionUID = 1L;

    private int privilegeId;
    private String privilegeName;
    private String description;
    private String observation;
    private boolean stateprivilege;

}
