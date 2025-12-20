package microservice.privilegio.microservice_privilegio.Model;

import lombok.Data;
import java.util.List;

@Data
public class AsignacionRequest {
    private Integer chargeId;
    private List<Integer> elementIds;
}