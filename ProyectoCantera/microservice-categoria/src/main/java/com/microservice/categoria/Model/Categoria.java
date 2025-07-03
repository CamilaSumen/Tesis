package com.microservice.categoria.Model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
    private int nCategoriaProductoId;
    @NotEmpty
    private String cDescripcion;
    @NotEmpty
    private boolean bEstado;
}
