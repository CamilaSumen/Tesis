package microservice.privilegio.microservice_privilegio.Repository.StoredProcedure;

public class StoredProcedureC {

    /*PA'S PARA LOS PRIVILEGIOS*/
    public static final String SEL_PRIVILEGIO= "{call PA_Privilegios_Sel_Listar}";
    public static final String INS_PRIVILEGIONUEVO = "{call PA_Privilegios_Ins_Nuevo(?,?,?)}";
    public static final String UPD_ELIMARPRIVILEGIOLOGICO = "{call PA_Privilegios_Upd_Eliminar(?)}";
    public static final String UPD_MODIFICARPRIVILEGIO = "{call PA_Privilegios_Upd_Modificar(?,?,?,?)}";


}