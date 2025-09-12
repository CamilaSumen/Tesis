package com.microservice.mesa.microservice_mesa.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mesa implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer tableId;
    private String tableCode;
    private Boolean tableBusy;
    private String username;
    private Boolean stateTable;
}
