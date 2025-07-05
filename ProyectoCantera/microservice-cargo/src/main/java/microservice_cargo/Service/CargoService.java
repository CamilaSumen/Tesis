package microservice_cargo.Service;
import microservice_cargo.Model.Cargo;
import microservice_cargo.Model.Empleado;
import microservice_cargo.Model.Privilegio;

import java.util.List;

public interface CargoService {

    /*SERVICES DE LOS CARGOS*/
    List<Cargo> listarCargos();
    void insertarCargo(Cargo cargo);
    void eliminarCargoLogico(int id);
    void modificarCargo(Cargo cargo);


    /*SERVICES DE LOS EMPLEADOS*/
    List<Empleado> listarEmpleados();
    void insertarEmpleado(Empleado empleado);
    void eliminarEmpleadoLogico(int id);
    void modificarEmpleado(Empleado empleado);


    /*SERVICES DE LOS EMPLEADOS*/
    List<Privilegio> listarPrivilegios();
    void insertarPrivilegio(Privilegio privilegio);
    void eliminarPrivilegioLogico(int id);
    void modificarPrivilegio(Privilegio privilegio);

}