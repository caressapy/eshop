package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testCreateWithNullProductID() {
        Product product = new Product();
        product.setProductName("Unnamed Product");
        product.setProductQuantity(5);
        Product createdProduct = productRepository.create(product);

        assertNotNull(createdProduct.getProductId());
        assertEquals("Unnamed Product", createdProduct.getProductName());
        assertEquals(5, createdProduct.getProductQuantity());
    }

    @Test
    void testEditWithNullProductID() {
        Product product = new Product();
        product.setProductName("Invalid Product");
        product.setProductQuantity(10);
        assertThrows(NullPointerException.class, () -> productRepository.edit(product));
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEdit() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Original Name");
        product.setProductQuantity(10);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        updatedProduct.setProductName("Updated Name");
        updatedProduct.setProductQuantity(20);
        productRepository.edit(updatedProduct);

        Product retrieved = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNotNull(retrieved);
        assertEquals("Updated Name", retrieved.getProductName());
        assertEquals(20, retrieved.getProductQuantity());
    }

    @Test
    void testDelete() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("To Be Deleted");
        product.setProductQuantity(5);
        productRepository.create(product);

        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNull(productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6"));
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setProductId("abc");
        product.setProductName("Find Me");
        product.setProductQuantity(15);
        productRepository.create(product);

        Product retrieved = productRepository.findById("abc");
        assertNotNull(retrieved);
        assertEquals("Find Me", retrieved.getProductName());
        assertEquals(15, retrieved.getProductQuantity());
    }

    @Test
    void testFindByIdNonExistent() {
        assertNull(productRepository.findById("non-existent"));
    }

    @Test
    void testDeleteNonExistent() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Existing Product");
        product.setProductQuantity(10);
        productRepository.create(product);

        productRepository.delete("non-existent");
        assertNotNull(productRepository.findById("1"));
    }

    @Test
    void testEditNonExistent() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Original Product");
        product.setProductQuantity(10);
        productRepository.create(product);

        Product nonExistent = new Product();
        nonExistent.setProductId("non-existent");
        nonExistent.setProductName("Updated Name");
        nonExistent.setProductQuantity(20);
        productRepository.edit(nonExistent);

        Product retrieved = productRepository.findById("1");
        assertEquals("Original Product", retrieved.getProductName());
        assertEquals(10, retrieved.getProductQuantity());
    }

    @Test
    void testCreateNullProduct() {
        assertThrows(NullPointerException.class, () -> productRepository.create(null));
    }

    @Test
    void testEditWithNullProduct() {
        assertThrows(NullPointerException.class, () -> productRepository.edit(null));
    }

    @Test
    void testDeleteWithNullId() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Existing Product");
        product.setProductQuantity(10);
        productRepository.create(product);

        productRepository.delete(null);
        assertNotNull(productRepository.findById("1"));
    }

    @Test
    void testFindByIdWithNullId() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Existing Product");
        product.setProductQuantity(10);
        productRepository.create(product);

        assertNull(productRepository.findById(null));
    }

    static class OrderRepositoryTest {
        OrderRepository orderRepository;
        List<Order> orders;

        @BeforeEach
        void setUp() {
            orderRepository = new OrderRepository();

            List<Product> products = new ArrayList<>();
            Product product1 = new Product();
            product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
            product1.setProductName("Sampo Cap Bambang");
            product1.setProductQuantity(2);
            products.add(product1);

            orders = new ArrayList<>();
            Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                    products, 1708560000L, "Safira Sudrajat");
            orders.add(order1);
            Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
                    products, 1708570000L, "Safira Sudrajat");
            orders.add(order2);
            Order order3 = new Order("e334ef40-9eff-4da8-9487-8ee697ecbf1e",
                    products, 1708570000L, "Bambang Sudrajat");
            orders.add(order3);
        }

        @Test
        void testSaveCreate() {
            Order order = orders.get(1);
            Order result = orderRepository.save(order);

            Order findResult = orderRepository.findById(orders.get(1).getId());
            assertEquals(order.getId(), result.getId());
            assertEquals(order.getId(), findResult.getId());
            assertEquals(order.getOrderTime(), findResult.getOrderTime());
            assertEquals(order.getAuthor(), findResult.getAuthor());
            assertEquals(order.getStatus(), findResult.getStatus());
        }

        @Test
        void testSaveUpdate() {
            Order order = orders.get(1);
            orderRepository.save(order);
            Order newOrder = new Order(order.getId(), order.getProducts(), order.getOrderTime(),
                    order.getAuthor(), OrderStatus.SUCCESS.getValue());
            Order result = orderRepository.save(newOrder);

            Order findResult = orderRepository.findById(orders.get(1).getId());
            assertEquals(order.getId(), result.getId());
            assertEquals(order.getId(), findResult.getId());
            assertEquals(order.getOrderTime(), findResult.getOrderTime());
            assertEquals(order.getAuthor(), findResult.getAuthor());
            assertEquals(OrderStatus.SUCCESS.getValue(), findResult.getStatus());
        }

        @Test
        void testFindByIdIfIdFound() {
            for (Order order : orders) {
                orderRepository.save(order);
            }

            Order findResult = orderRepository.findById(orders.get(1).getId());
            assertEquals(orders.get(1).getId(), findResult.getId());
            assertEquals(orders.get(1).getOrderTime(), findResult.getOrderTime());
            assertEquals(orders.get(1).getAuthor(), findResult.getAuthor());
            assertEquals(orders.get(1).getStatus(), findResult.getStatus());
        }

        @Test
        void testFindByIdIfIdNotFound() {
            for (Order order : orders) {
                orderRepository.save(order);
            }

            Order findResult = orderRepository.findById("zczc");
            assertNull(findResult);
        }

        @Test
        void testFindAllByAuthorIfAuthorCorrect() {
            for (Order order : orders) {
                orderRepository.save(order);
            }

            List<Order> orderList = orderRepository.findAllByAuthor(
                    orders.get(1).getAuthor());
            assertEquals(2, orderList.size());
        }

        @Test
        void testFindAllByAuthorIfAllLowecase() {
            orderRepository.save(orders.get(1));

            List<Order> orderList = orderRepository.findAllByAuthor(
                    orders.get(1).getAuthor().toLowerCase());
            assertTrue(orderList.isEmpty());
        }
    }
}