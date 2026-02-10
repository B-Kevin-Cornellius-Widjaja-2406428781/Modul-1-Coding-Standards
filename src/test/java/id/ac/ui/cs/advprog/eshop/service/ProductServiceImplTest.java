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
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Usep");
        updatedProduct.setProductQuantity(200);

        Product expectedResult = new Product();
        expectedResult.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        expectedResult.setProductName("Sampo Cap Usep");
        expectedResult.setProductQuantity(200);

        when(productRepository.update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct))
                .thenReturn(expectedResult);

        Product result = productService.update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct);

        assertNotNull(result);
        assertEquals("Sampo Cap Usep", result.getProductName());
        assertEquals(200, result.getProductQuantity());
        verify(productRepository, times(1)).update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct);
    }

    @Test
    void testUpdate_ProductNotFound() {
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Usep");
        updatedProduct.setProductQuantity(200);

        when(productRepository.update("non-existent-id", updatedProduct)).thenReturn(null);

        Product result = productService.update("non-existent-id", updatedProduct);

        assertNull(result);
        verify(productRepository, times(1)).update("non-existent-id", updatedProduct);
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
