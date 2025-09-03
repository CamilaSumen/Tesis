package com.microservice.categoria.Repository.StoredProcedure;

public class StoredProcedureC {

    /*PA'S PARA LOS TURNOS*/
    public static final String SEL_CATEGORIA= "{call PA_CategoriaProducto_Sel_ListarCategorias}";
    public static final String INS_CATEGORIANUEVO = "{call PA_CategoriaProducto_Ins_NuevaCategoria(?,?,?)}";
    public static final String UPD_ELIMARCATEGORIALOGICO = "{call PA_CategoriaProducto_Upd_EliminarCategoria(?)}";
    public static final String UPD_MODIFICARCATEGORIA = "{call PA_CategoriaProducto_Upd_NuevaCategoria(?,?,?,?)}";

}