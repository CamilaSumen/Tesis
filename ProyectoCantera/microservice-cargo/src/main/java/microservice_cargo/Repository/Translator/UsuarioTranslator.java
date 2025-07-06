package microservice_cargo.Repository.Translator;

import microservice_cargo.Model.Turno;
import microservice_cargo.Model.Usuario;

public class UsuarioTranslator {

    private Integer userId;
    private String username;
    private String password;

    private Integer privilegeId;
    private String privilegeName;
    private Integer shiftId;
    private String shiftName;
    private Integer employeeId;
    private String firstName;
    private String lastNameFather;
    private String lastNameMother;
    private String dni;
    private String email;
    private String phone;
    private Boolean status;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Usuario toUsuarioDTO() {
        Usuario usuario = new Usuario();

        usuario.setUserId(this.userId);
        usuario.setUsername(this.username);
        usuario.setPassword(this.password);

        usuario.setPrivilegeId(this.privilegeId);
        usuario.setPrivilegeName(this.privilegeName);

        usuario.setShiftId(this.shiftId);
        usuario.setShiftName(this.shiftName);

        usuario.setEmployeeId(this.employeeId);
        usuario.setFirstName(this.firstName);
        usuario.setLastNameFather(this.lastNameFather);
        usuario.setLastNameMother(this.lastNameMother);
        usuario.setDni(this.dni);
        usuario.setEmail(this.email);
        usuario.setPhone(this.phone);

        usuario.setStatus(this.status);

        return usuario;
    }

}
