package microservice.privilegio.microservice_privilegio.Repository.StoredProcedure;

public class StoredProcedureC {

    /*PA'S PARA ELEMENTOS Y PRIVILEGIOS*/
    public static final String SEL_ELEMENTO_LISTAR = "{call PA_Elemento_Sel_Listar}";
    public static final String SEL_ELEMENTO_DISPONIBLES = "{call PA_Elemento_Sel_Disponibles(?)}";
    public static final String SEL_ELEMENTOXCARGO_PORCARGO = "{call PA_ElementoxCargo_Sel_PorCargo(?)}";
    public static final String INS_ELEMENTOXCARGO = "{call PA_ElementoxCargo_Ins_Nuevo(?,?)}";
    public static final String DEL_ELEMENTOXCARGO = "{call PA_ElementoxCargo_Del_Eliminar(?)}";
    public static final String DEL_ELEMENTOXCARGO_PORELEMENTO = "{call PA_ElementoxCargo_Del_PorElementoCargo(?,?)}";
    public static final String INS_ELEMENTOXCARGO_BATCH = "{call PA_ElementoxCargo_Ins_Batch(?,?)}";
}