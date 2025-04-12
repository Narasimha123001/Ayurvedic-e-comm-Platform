package com.techtricks.ayurvedic.controllers;

import com.techtricks.ayurvedic.dto.CartDTO;
import com.techtricks.ayurvedic.exceptions.ResourceNotFoundException;
import com.techtricks.ayurvedic.exceptions.UserNotFoundException;
import com.techtricks.ayurvedic.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart/")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/users/{userId}/cart/products/{productId}/quantity/{quantity}")
    public ResponseEntity<CartDTO> addProductToCart(@PathVariable Long userId, @PathVariable Long productId, @PathVariable Integer quantity)
            throws ResourceNotFoundException, UserNotFoundException {
        CartDTO cartDTO = cartService.addProductToCart(userId, productId, quantity);
        return new ResponseEntity<>(cartDTO, HttpStatus.CREATED);
    }

    @GetMapping("/admin/carts")
    public ResponseEntity<List<CartDTO>> getCarts() {

        List<CartDTO> cartDTOs = cartService.getAllCarts();

        return new ResponseEntity<>(cartDTOs, HttpStatus.FOUND);
    }

    @GetMapping("/users/{id}/cart")
    public ResponseEntity<CartDTO> getCartByUserId(@PathVariable Long id) throws ResourceNotFoundException {
        CartDTO cartDTO = cartService.getCart(id);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    @PutMapping("/carts/{cartId}/products/{productId}/quantity/{quantity}")
    public ResponseEntity<CartDTO> updateCartProduct(@PathVariable Long cartId, @PathVariable Long productId, @PathVariable Integer quantity) throws ResourceNotFoundException {
        CartDTO cartDTO = cartService.updateProductQuantityInCart(cartId, productId, quantity);

        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    @DeleteMapping("/carts/{cartId}/product/{productId}")
    public ResponseEntity<String> deleteProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) throws ResourceNotFoundException {
        String status = cartService.deleteProductFromCart(cartId, productId);

        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}