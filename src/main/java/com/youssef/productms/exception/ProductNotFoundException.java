package com.youssef.productms.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("Produit introuvable avec l'id : " + id);
    }
}
