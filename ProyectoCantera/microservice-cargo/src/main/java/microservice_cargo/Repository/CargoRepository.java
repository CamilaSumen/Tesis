package microservice_cargo.Repository;
import microservice_cargo.Model.Cargo;
import microservice_cargo.Model.Empleado;

import java.util.List;

public interface CargoRepository {


    /*REPOSITORY PARA LOS CARGOS*/
    List<Cargo> listarCargos();
    void insertarCargo(Cargo cargo);
    void eliminarCargoLogico(int id);
    void modificarCargo(Cargo cargo);

    /*REPOSITPRY PARA LOS EMPLEADOS*/
    List<Empleado> listarEmpleados();
    void insertarEmpleado(Empleado empleado);
    void eliminarEmpleadoLogico(int id);
    void modificarEmpleado(Empleado empleado);

}