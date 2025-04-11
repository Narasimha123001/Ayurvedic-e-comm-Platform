package com.techtricks.ayurvedic.exceptions;

import org.springframework.http.HttpStatus;

public class ProductAlreadyExistsException extends Exception {

    public ProductAlreadyExistsException(String message, HttpStatus internalServerError) {
        super(message);
    }

}
