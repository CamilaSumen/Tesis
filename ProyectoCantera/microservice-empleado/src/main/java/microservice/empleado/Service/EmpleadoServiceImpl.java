package microservice.empleado.Service;

import microservice.empleado.Model.Cliente;
import microservice.empleado.Model.Empleado;
import microservice.empleado.Repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    /* IMPLEMENTACION PARA LOS EMPLEADOS*/
    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.listarEmpleados();
    }

    @Override
    public void insertarEmpleado(Empleado empleado) {
        empleadoRepository.insertarEmpleado(empleado);
    }

    @Override
    public void eliminarEmpleadoLogico(int id) {
        empleadoRepository.eliminarEmpleadoLogico(id);
    }

    @Override
    public void modificarEmpleado(Empleado empleado) {
        empleadoRepository.modificarEmpleado(empleado);
    }

    @Override
    public List<Cliente> listarClientes(Cliente cliente) {
        return empleadoRepository.listarClientes(cliente);
    }

}
