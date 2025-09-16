package microservice_categoriainsumo.Repository.StoredProcedure;

public class StoredProcedureC {

    /*PA'S PARA LAS CATEGORIAINSUMOS*/
    public static final String SEL_CATEGORIAINSUMO= "{call PA_Categoriainsumo_Sel_ListarCategoriainsumo}";
    public static final String INS_CATEGORIAINSUMONUEVO = "{call PA_Categoriainsumo_Ins_NuevaCategoriainsumo(?,?,?)}";
    public static final String UPD_ELIMARCATEGORIAINSUMOLOGICO = "{call PA_Categoriainsumo_Upd_EliminarCategoriainsumo(?)}";
    public static final String UPD_MODIFICARCATEGORIAINSUMO = "{call PA_Categoriainsumo_Upd_ActualizarCategoriainsumo(?,?,?,?)}";

}