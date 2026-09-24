package com.youssef.productms.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(

        @NotBlank(message = "Le nom est obligatoire")
        String name,

        String description,

        @NotNull(message = "Le prix est obligatoire")
        @DecimalMin(value = "0.0", inclusive = true, message = "Le prix doit être positif")
        BigDecimal price,

        @NotNull(message = "La quantité est obligatoire")
        @Min(value = 0, message = "La quantité doit être positive")
        Integer quantity
) {
}
