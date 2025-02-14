package id.ac.ui.cs.advprog.eshop.model;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Product {
    private String productId;
    private String productName;
    private int productQuantity;

    public String getProductID() {
        return productId;
    }

    public void setProductID(String productID) {
        this.productId = productID;
    }

    public String getProductName() {
        return productName;
    }

}
