package microservice_categoriainsumo.Repository.Translator;

import microservice_categoriainsumo.Model.Categoriainsumo;

public class CategoriainsumoTranslator {

    private Integer inputcategoryId;
    private String inputcategoryname;
    private String inputcategorydescription;
    private Boolean stateinputcategory;

    public void setInputcategoryId(Integer inputcategoryId) {
        this.inputcategoryId = inputcategoryId;
    }

    public void setInputcategoryname(String inputcategoryname) {
        this.inputcategoryname = inputcategoryname;
    }

    public void setInputcategorydescription(String inputcategorydescription) {
        this.inputcategorydescription = inputcategorydescription;
    }

    public void setStateinputcategory(Boolean stateinputcategory) {
        this.stateinputcategory = stateinputcategory;
    }


    public Categoriainsumo toCategoriainsumoDTO() {
        Categoriainsumo categoriainsumo = new Categoriainsumo();
        categoriainsumo.setInputcategoryId(this.inputcategoryId);
        categoriainsumo.setInputcategoryname(this.inputcategoryname);
        categoriainsumo.setInputcategorydescription(this.inputcategorydescription);
        categoriainsumo.setStateinputcategory(this.stateinputcategory);
        return categoriainsumo;
    }

}
