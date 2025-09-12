package microservice_adicional.Repository.StoredProcedure;

public class StoredProcedureC {

    /*PA'S PARA LAS ADICIONALS*/
    public static final String SEL_ADICIONAL= "{call PA_Adicional_Sel_ListarAdicional}";
    public static final String INS_ADICIONALNUEVO = "{call PA_Adicional_Ins_NuevaAdicional(?,?,?,?)}";
    public static final String UPD_ELIMARADICIONALLOGICO = "{call PA_Adicional_Upd_EliminarAdicional(?)}";
    public static final String UPD_MODIFICARADICIONAL = "{call PA_Adicional_Upd_Adicional(?,?,?,?,?)}";

}