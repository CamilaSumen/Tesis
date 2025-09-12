package microservice_producto.Repository.Translator;

import microservice_producto.Model.Producto;

import java.time.LocalTime;

public class ProductoTranslator {


    private Integer productId;
    private Integer categoryId;
    private String categoryname;
    private String productName;
    private String productDescription;
    private String productimg;
    private Double productPryce;
    private LocalTime productRegisterDate;
    private Boolean stateproduct;

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductimg(String productimg) {
        this.productimg = productimg;
    }

    public void setProductPryce(Double productPryce) {
        this.productPryce = productPryce;
    }

    public void setProductRegisterDate(LocalTime productRegisterDate) {
        this.productRegisterDate = productRegisterDate;
    }

    public void setStateproduct(Boolean stateproduct) {
        this.stateproduct = stateproduct;
    }

    public Producto toProductoDTO() {
        Producto producto = new Producto();
        producto.setProductId(this.productId);
        producto.setProductName(this.productName);
        producto.setCategoryname(this.categoryname);
        producto.setCategoryId(this.categoryId);
        producto.setProductDescription(this.productDescription);
        producto.setProductimg(this.productimg);
        producto.setProductPryce(this.productPryce);
        producto.setProductRegisterDate(this.productRegisterDate);
        producto.setStateproduct(this.stateproduct);
        return producto;
    }
}
