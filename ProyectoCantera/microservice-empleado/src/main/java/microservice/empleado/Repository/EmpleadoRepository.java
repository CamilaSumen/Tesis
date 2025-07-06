package microservice.empleado.Repository;

import microservice.empleado.Model.Empleado;

import java.util.List;

public interface EmpleadoRepository {

    /*REPOSITPRY PARA LOS EMPLEADOS*/
    List<Empleado> listarEmpleados();
    void insertarEmpleado(Empleado empleado);
    void eliminarEmpleadoLogico(int id);
    void modificarEmpleado(Empleado empleado);


}
