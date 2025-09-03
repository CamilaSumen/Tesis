package microservice.turno.Repository.StoredProcedure;

public class StoredProcedureC {

    /*PA'S PARA LOS TURNOS*/
    public static final String SEL_TURNO= "{call PA_Turno_Sel_Listar}";
    public static final String INS_TURNONUEVO = "{call PA_Turno_Ins_Nuevo(?,?,?)}";
    public static final String UPD_ELIMARTURNOLOGICO = "{call PA_Turno_Upd_Eliminar(?)}";
    public static final String UPD_MODIFICARTURNO = "{call PA_Turno_Upd_Modificar(?,?,?,?,?)}";

}