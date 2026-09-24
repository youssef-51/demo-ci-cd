package com.youssef.productms.service;

import com.youssef.productms.dto.ProductRequest;
import com.youssef.productms.dto.ProductResponse;
import com.youssef.productms.exception.ProductNotFoundException;
import com.youssef.productms.model.Product;
import com.youssef.productms.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    @Test
    void createSavesAndReturnsProduct() {
        ProductRequest request = new ProductRequest("Clavier", "Clavier mécanique", new BigDecimal("299.00"), 10);
        Product saved = new Product(1L, "Clavier", "Clavier mécanique", new BigDecimal("299.00"), 10);

        when(repository.save(any(Product.class))).thenReturn(saved);

        ProductResponse response = service.create(request);

        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.name()).isEqualTo("Clavier");
        assertThat(response.quantity()).isEqualTo(10);
    }

    @Test
    void getByIdThrowsWhenNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getById(99L))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void deleteThrowsWhenProductDoesNotExist() {
        when(repository.existsById(5L)).thenReturn(false);

        assertThatThrownBy(() -> service.delete(5L))
                .isInstanceOf(ProductNotFoundException.class);
    }
}
