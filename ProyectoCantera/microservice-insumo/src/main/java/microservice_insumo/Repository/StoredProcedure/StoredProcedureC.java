package microservice_insumo.Repository.StoredProcedure;

public class StoredProcedureC {

    /*PA'S PARA LAS INSUMOS*/
    public static final String SEL_INSUMO= "{call PA_Insumo_Sel_ListarInsumo}";
    public static final String INS_INSUMONUEVO = "{call PA_Insumo_Ins_NuevaInsumo(?,?,?,?,?)}";
    public static final String UPD_ELIMARINSUMOLOGICO = "{call PA_Insumo_Upd_EliminarInsumo(?)}";
    public static final String UPD_MODIFICARINSUMO = "{call PA_Insumo_Upd_ActualizarInsumo(?,?,?,?,?,?)}";

}