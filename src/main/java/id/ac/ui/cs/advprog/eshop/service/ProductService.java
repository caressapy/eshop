package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public Product update(String productId, Product product);
    public Product delete(String productId);
    public Product findById(String id);
    public List<Product> findAll();
}