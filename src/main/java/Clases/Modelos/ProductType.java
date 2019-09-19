package Clases.Modelos;

public class ProductType {
    private int id;
    private String productType;
    private String description;

    public ProductType(int id, String productType, String description) {
        this.id = id;
        this.productType = productType;
        this.description = description;
    }

    public ProductType(int id, String productType) {
        this.id = id;
        this.productType = productType;
    }

    public ProductType(String productType, String description) {
        this.productType = productType;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
