package microservice.empleado.Repository.StoredProcedure;

public class StoredProcedureC {

    /*PA'S PARA LOS EMPLEADOS*/
    public static final String SEL_EMPLEADO= "{call PA_Empleado_Sel_Listar}";
    public static final String INS_EMPLEADONUEVO = "{call PA_Empleado_Ins_Nuevo(?,?,?,?,?,?,?,?,?,?)}";
    public static final String UPD_ELIMAREMPLEADOLOGICO = "{call PA_Empleado_Upd_Eliminar(?)}";
    public static final String UPD_MODIFICAREMPLEADO = "{call PA_Empleado_Upd_Modificar(?,?,?,?,?,?,?,?,?,?)}";


}