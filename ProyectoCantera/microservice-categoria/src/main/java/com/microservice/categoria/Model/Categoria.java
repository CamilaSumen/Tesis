package com.microservice.categoria.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer categoryId;
    private String nameCategory;
    private String descriptionCategory;
    private String imageCategory;
    private Boolean stateCategory;
}
