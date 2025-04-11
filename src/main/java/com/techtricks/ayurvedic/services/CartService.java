package com.techtricks.ayurvedic.services;

import com.techtricks.ayurvedic.dto.CartDTO;
import com.techtricks.ayurvedic.exceptions.ResourceNotFoundException;
import com.techtricks.ayurvedic.exceptions.UserNotFoundException;
import com.techtricks.ayurvedic.models.Cart;
import com.techtricks.ayurvedic.models.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CartService {

    public Cart createCartForUser(User user);
    CartDTO addProductToCart(Long cartId, Long productId, Integer quantity) throws ResourceNotFoundException, UserNotFoundException;


    List<CartDTO> getAllCarts();

    public CartDTO getCart(Long id) throws ResourceNotFoundException;

    CartDTO updateProductQuantityInCart(Long cartId, Long productId, Integer quantity) throws ResourceNotFoundException;

    void updateProductInCarts(Long cartId, Long productId) throws ResourceNotFoundException;

    String deleteProductFromCart(Long cartId, Long productId) throws ResourceNotFoundException;
}
