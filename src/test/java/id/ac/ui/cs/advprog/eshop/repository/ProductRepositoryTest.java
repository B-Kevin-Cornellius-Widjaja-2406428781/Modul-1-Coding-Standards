package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

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

    // FIND BY ID TESTS

    @Test
    void testFindById_ProductExists() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product foundProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertNotNull(foundProduct);
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", foundProduct.getProductId());
        assertEquals("Sampo Cap Bambang", foundProduct.getProductName());
        assertEquals(100, foundProduct.getProductQuantity());
    }

    @Test
    void testFindById_ProductNotFound() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product foundProduct = productRepository.findById("non-existent-id");

        assertNull(foundProduct);
    }

    @Test
    void testFindById_EmptyRepository() {
        Product foundProduct = productRepository.findById("any-id");

        assertNull(foundProduct);
    }

    // UPDATE TESTS

    @Test
    void testUpdate_ProductExists() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Usep");
        updatedProduct.setProductQuantity(200);

        Product result = productRepository.update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct);

        assertNotNull(result);
        assertEquals("Sampo Cap Usep", result.getProductName());
        assertEquals(200, result.getProductQuantity());
    }

    @Test
    void testUpdate_ProductNotFound() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("New Name");
        updatedProduct.setProductQuantity(999);

        Product result = productRepository.update("non-existent-id", updatedProduct);

        assertNull(result);
    }

    @Test
    void testUpdate_EmptyRepository() {
        Product updatedProduct = new Product();
        updatedProduct.setProductName("New Name");
        updatedProduct.setProductQuantity(999);

        Product result = productRepository.update("any-id", updatedProduct);

        assertNull(result);
    }

    @Test
    void testUpdate_OnlyNameChanged() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("New Product Name");
        updatedProduct.setProductQuantity(100);

        Product result = productRepository.update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct);

        assertNotNull(result);
        assertEquals("New Product Name", result.getProductName());
        assertEquals(100, result.getProductQuantity());
    }

    @Test
    void testUpdate_OnlyQuantityChanged() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Bambang");
        updatedProduct.setProductQuantity(500);

        Product result = productRepository.update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct);

        assertNotNull(result);
        assertEquals("Sampo Cap Bambang", result.getProductName());
        assertEquals(500, result.getProductQuantity());
    }

    @Test
    void testUpdate_OneOfManyProducts() {
        Product product1 = new Product();
        product1.setProductId("id-1");
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("id-2");
        product2.setProductName("Product 2");
        product2.setProductQuantity(20);
        productRepository.create(product2);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Updated Product 1");
        updatedProduct.setProductQuantity(99);

        Product result = productRepository.update("id-1", updatedProduct);

        assertNotNull(result);
        assertEquals("Updated Product 1", result.getProductName());
        assertEquals(99, result.getProductQuantity());

        // Verify product2 is unchanged
        Product unchanged = productRepository.findById("id-2");
        assertEquals("Product 2", unchanged.getProductName());
        assertEquals(20, unchanged.getProductQuantity());
    }

    // DELETE TESTS

    @Test
    void testDeleteById_ProductExists() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product deletedProduct = productRepository.deleteById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertNotNull(deletedProduct);
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", deletedProduct.getProductId());

        // Verify product is actually removed
        Product foundProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNull(foundProduct);
    }

    @Test
    void testDeleteById_ProductNotFound() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product deletedProduct = productRepository.deleteById("non-existent-id");

        assertNull(deletedProduct);

        // Verify original product still exists
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
    }

    @Test
    void testDeleteById_EmptyRepository() {
        Product deletedProduct = productRepository.deleteById("any-id");

        assertNull(deletedProduct);
    }

    @Test
    void testDeleteById_DeleteOneOfMany() {
        Product product1 = new Product();
        product1.setProductId("id-1");
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("id-2");
        product2.setProductName("Product 2");
        product2.setProductQuantity(20);
        productRepository.create(product2);

        Product deletedProduct = productRepository.deleteById("id-1");

        assertNotNull(deletedProduct);
        assertEquals("id-1", deletedProduct.getProductId());

        // Verify only product2 remains
        assertNull(productRepository.findById("id-1"));
        assertNotNull(productRepository.findById("id-2"));
    }
}
