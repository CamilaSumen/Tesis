package microservice_cargo.Repository.StoredProcedure;

public class StoredProcedureC {
    /*PA'S PARA LOS CARGOS*/
    public static final String SEL_CARGO= "{call PA_Cargo_Sel_ListarCargo}";
    public static final String INS_CARGONUEVO = "{call PA_Cargo_Ins_NuevoCargo(?,?,?)}";
    public static final String UPD_ELIMARCARGOLOGICO = "{call PA_Cargo_Upd_EliminarCargo(?)}";
    public static final String UPD_MODIFICARCARGO = "{call PA_Cargo_Upd_ModificarCargo(?,?,?,?)}";

    /*PA'S PARA LOS EMPLEADOS*/
    public static final String SEL_EMPLEADO= "{call PA_Empleado_Sel_Listar}";
    public static final String INS_EMPLEADONUEVO = "{call PA_Empleado_Ins_Nuevo(?,?,?,?,?,?,?,?,?,?)}";
    public static final String UPD_ELIMAREMPLEADOLOGICO = "{call PA_Empleado_Upd_Eliminar(?)}";
    public static final String UPD_MODIFICAREMPLEADO = "{call PA_Empleado_Upd_Modificar(?,?,?,?,?,?,?,?,?,?)}";

}