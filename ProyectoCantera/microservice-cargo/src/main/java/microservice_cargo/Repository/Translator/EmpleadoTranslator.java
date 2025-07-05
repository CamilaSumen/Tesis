package microservice_cargo.Repository.Translator;

import microservice_cargo.Model.Cargo;
import microservice_cargo.Model.Empleado;

import java.time.LocalDate;

public class EmpleadoTranslator {

    private int idEmployer;
    private String firstName;
    private String lastNameFather;
    private String lastNameMother;
    private LocalDate birthDate;
    private String address;
    private String phone;
    private String dni;
    private String email;

    private Integer chargeId;
    private LocalDate entryDate;

    public void setIdEmployer(int idEmployer) {
        this.idEmployer = idEmployer;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastNameFather(String lastNameFather) {
        this.lastNameFather = lastNameFather;
    }

    public void setLastNameMother(String lastNameMother) {
        this.lastNameMother = lastNameMother;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setChargeId(Integer chargeId) {
        this.chargeId = chargeId;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }


    public Empleado toEmpleadoDTO() {
        Empleado empleado = new Empleado();
        empleado.setIdEmployer(this.idEmployer);
        empleado.setFirstName(this.firstName);
        empleado.setLastNameFather(this.lastNameFather);
        empleado.setLastNameMother(this.lastNameMother);
        empleado.setBirthDate(this.birthDate);
        empleado.setAddress(this.address);
        empleado.setPhone(this.phone);
        empleado.setDni(this.dni);
        empleado.setEmail(this.email);
        empleado.setChargeId(this.chargeId);
        empleado.setEntryDate(this.entryDate);
        return empleado;
    }
}
