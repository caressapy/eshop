package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    Product product;
    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId("123456789");
        this.product.setProductName("Produk1");
        this.product.setProductQuantity(100);
    }

    @Test
    void testGetProductId() {
        assertEquals("123456789", this.product.getProductId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Produk1", this.product.getProductName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.product.getProductQuantity());
    }
}



