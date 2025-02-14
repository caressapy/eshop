package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();
    private long id = 1;
    Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    public Product create(Product product) {
        product.setProductID(Long.toString(this.id));
        this.id++;
        productData.add(product);
        return product;
    }

    public Product update(String productId, Product updatedProduct) {
        Product product = findById(productId);
        if (product == null) {
            return null;
        }
        product.setProductName(updatedProduct.getProductName());
        product.setProductQuantity(updatedProduct.getProductQuantity());
        return product;
    }

    public Product delete(String productID) {
        Product product = findById(productID);
        if (product == null) {
            return null;
        }
        productData.remove(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String id) {
        for (Product product : productData) {
            if (product.getProductID().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public void printAllProducts() {
        logger.info("List of Products:");
        for (Product product : productData) {
            logger.info("Product ID: " + product.getProductID());
            logger.info("Product Name: " + product.getProductName());
            logger.info("Product Quantity: " + product.getProductQuantity());
        }
        logger.info("------------------------");
    }
}