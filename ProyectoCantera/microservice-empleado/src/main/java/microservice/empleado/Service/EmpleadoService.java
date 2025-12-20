package microservice.empleado.Service;

import microservice.empleado.Model.Cliente;
import microservice.empleado.Model.Empleado;

import java.util.List;

public interface EmpleadoService {
    List<Empleado> listarEmpleados();
    void insertarEmpleado(Empleado empleado);
    void eliminarEmpleadoLogico(int id);
    void modificarEmpleado(Empleado empleado);

    List<Cliente> listarClientes(Cliente cliente);
}
