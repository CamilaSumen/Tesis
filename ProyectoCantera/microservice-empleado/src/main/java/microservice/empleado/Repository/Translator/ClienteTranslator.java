package microservice.empleado.Repository.Translator;

import microservice.empleado.Model.Cliente;

import java.time.LocalDate;

public class ClienteTranslator {


    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public Cliente toClienteDTO() {
        Cliente cliente = new Cliente();
        cliente.setDni(this.dni);
        cliente.setNombre(this.nombre);
        cliente.setApellido(this.apellido);
        cliente.setEmail(this.email);
        cliente.setTelefono(this.telefono);
        return cliente;
    }
}
