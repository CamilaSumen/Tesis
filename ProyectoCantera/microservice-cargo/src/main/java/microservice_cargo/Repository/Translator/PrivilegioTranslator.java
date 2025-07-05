package microservice_cargo.Repository.Translator;

import microservice_cargo.Model.Privilegio;

public class PrivilegioTranslator {

    private int privilegeId;
    private String privilegeName;
    private String description;
    private String observation;
    private boolean stateprivilege;


    public void setPrivilegeId(int privilegeId) {
        this.privilegeId = privilegeId;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public void setStateprivilege(boolean stateprivilege) {
        this.stateprivilege = stateprivilege;
    }

    public Privilegio toPrivilegioDTO() {
        Privilegio privilegio = new Privilegio();
        privilegio.setPrivilegeId(this.privilegeId);
        privilegio.setPrivilegeName(this.privilegeName);
        privilegio.setDescription(this.description);
        privilegio.setObservation(this.observation);
        privilegio.setStateprivilege(this.stateprivilege);
        return privilegio;
    }
}
