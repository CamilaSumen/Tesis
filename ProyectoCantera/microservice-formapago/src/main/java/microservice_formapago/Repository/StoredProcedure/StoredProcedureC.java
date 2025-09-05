package microservice_formapago.Repository.StoredProcedure;

public class StoredProcedureC {

    /*PA'S PARA LOS FORMAPAGOS*/
    public static final String SEL_FORMAPAGO= "{call PA_FormaPago_Sel_ListarFormaPago}";
    public static final String INS_FORMAPAGONUEVO = "{call PA_FormaPago_Ins_NuevoFormaPago(?,?)}";
    public static final String UPD_ELIMARFORMAPAGOLOGICO = "{call PA_FormaPago_Upd_EliminarFormaPago(?)}";
    public static final String UPD_MODIFICARFORMAPAGO = "{call PA_FormaPago_Upd_FormaPago(?,?,?)}";


}