package microservice.privilegio.microservice_privilegio.Repository.Translator;


import microservice.privilegio.microservice_privilegio.Model.Elemento;

public class ElementoTranslator {

    private Integer elementId;
    private String elementCode;
    private String module;
    private String elementName;
    private String elementCommand;

    // Getters
    public Integer getElementId() { return elementId; }
    public String getElementCode() { return elementCode; }
    public String getModule() { return module; }
    public String getElementName() { return elementName; }
    public String getElementCommand() { return elementCommand; }

    // Setters
    public void setElementId(Integer elementId) { this.elementId = elementId; }
    public void setElementCode(String elementCode) { this.elementCode = elementCode; }
    public void setModule(String module) { this.module = module; }
    public void setElementName(String elementName) { this.elementName = elementName; }
    public void setElementCommand(String elementCommand) { this.elementCommand = elementCommand; }

    public Elemento toElementoDTO() {
        Elemento elemento = new Elemento();
        elemento.setElementId(this.elementId);
        elemento.setElementCode(this.elementCode);
        elemento.setModule(this.module);
        elemento.setElementName(this.elementName);
        elemento.setElementCommand(this.elementCommand);
        return elemento;
    }
}