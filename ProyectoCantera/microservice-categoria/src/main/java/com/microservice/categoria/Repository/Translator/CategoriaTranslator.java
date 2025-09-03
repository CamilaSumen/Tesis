package com.microservice.categoria.Repository.Translator;

import com.microservice.categoria.Model.Categoria;

public class CategoriaTranslator {

    private Integer categoryId;
    private String nameCategory;
    private String descriptionCategory;
    private String imageCategory;
    private boolean stateCategory;


    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public void setDescriptionCategory(String descriptionCategory) {
        this.descriptionCategory = descriptionCategory;
    }

    public void setImageCategory(String imageCategory) {
        this.imageCategory = imageCategory;
    }

    public void setStateCategory(boolean stateCategory) {
        this.stateCategory = stateCategory;
    }

    public Categoria toCategoriaDTO() {
        Categoria categoria = new Categoria();
        categoria.setCategoryId(this.categoryId);
        categoria.setNameCategory(this.nameCategory);
        categoria.setDescriptionCategory(this.descriptionCategory);
        categoria.setImageCategory(this.imageCategory);
        categoria.setStateCategory(this.stateCategory);
        return categoria;
    }
}