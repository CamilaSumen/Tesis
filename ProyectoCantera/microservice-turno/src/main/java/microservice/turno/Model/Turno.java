package microservice.turno.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Turno implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer shiftId;
    private String nameshift;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean stateshift;

}
