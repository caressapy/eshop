package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class ProductServiceTest {
    private ProductService productService;
    private Product existingProduct;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(); // Sesuaikan dengan implementasi service Anda
        existingProduct = new Product();
        existingProduct.setProductID("123456789");
        existingProduct.setProductName("Product1");
        existingProduct.setProductQuantity(100);
        productService.create(existingProduct);
    }

    @Test
    void testUpdateProduct_Success() {
        Product updatedProduct = new Product();
        updatedProduct.setProductID("1");
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(150);

        Product result = productService.update("1", updatedProduct);
        assertNotNull(result, "Product should be updated");
        assertEquals("Updated Product", result.getProductName(), "Product name should be updated");
        assertEquals(150, result.getProductQuantity(), "Product quantity should be updated");
    }

    @Test
    void testUpdateProduct_Fail_ProductNotFound() {
        Product nonExistentProduct = new Product();
        Product result = productService.update("99", nonExistentProduct);
        assertNull(result, "Updating non-existent product should return null");
    }

    @Test
    void testDeleteProduct_Success() {
        Product deletedProduct = productService.delete("1");
        assertNotNull(deletedProduct, "Deleted product should not be null");
        assertEquals("Test Product", deletedProduct.getProductName(), "Deleted product name should match");
    }

    @Test
    void testDeleteProduct_Fail_ProductNotFound() {
        Product result = productService.delete("99");
        assertNull(result, "Deleting non-existent product should return null");
    }

}
