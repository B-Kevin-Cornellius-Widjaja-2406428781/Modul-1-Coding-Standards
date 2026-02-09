package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
    }

    // UPDATE/EDIT TESTS

    @Test
    void testUpdate_ProductExists() {
        when(productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Usep");
        updatedProduct.setProductQuantity(200);

        Product result = productService.update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct);

        assertNotNull(result);
        assertEquals("Sampo Cap Usep", result.getProductName());
        assertEquals(200, result.getProductQuantity());
        verify(productRepository, times(1)).findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
    }

    @Test
    void testUpdate_ProductNotFound() {
        when(productRepository.findById("non-existent-id")).thenReturn(null);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Usep");
        updatedProduct.setProductQuantity(200);

        Product result = productService.update("non-existent-id", updatedProduct);

        assertNull(result);
        verify(productRepository, times(1)).findById("non-existent-id");
    }

    @Test
    void testUpdate_OnlyNameChanged() {
        when(productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("New Product Name");
        updatedProduct.setProductQuantity(100); // Same quantity

        Product result = productService.update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct);

        assertNotNull(result);
        assertEquals("New Product Name", result.getProductName());
        assertEquals(100, result.getProductQuantity());
    }

    @Test
    void testUpdate_OnlyQuantityChanged() {
        when(productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Bambang"); // Same name
        updatedProduct.setProductQuantity(500);

        Product result = productService.update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct);

        assertNotNull(result);
        assertEquals("Sampo Cap Bambang", result.getProductName());
        assertEquals(500, result.getProductQuantity());
    }

    // DELETE TESTS

    @Test
    void testDeleteById_ProductExists() {
        when(productRepository.deleteById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(product);

        Product deletedProduct = productService.deleteById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertNotNull(deletedProduct);
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", deletedProduct.getProductId());
        verify(productRepository, times(1)).deleteById("eb558e9f-1c39-460e-8860-71af6af63bd6");
    }

    @Test
    void testDeleteById_ProductNotFound() {
        when(productRepository.deleteById("non-existent-id")).thenReturn(null);

        Product deletedProduct = productService.deleteById("non-existent-id");

        assertNull(deletedProduct);
        verify(productRepository, times(1)).deleteById("non-existent-id");
    }
}
