package com.techtricks.ayurvedic.services;


import com.techtricks.ayurvedic.dto.OrderDTO;
import com.techtricks.ayurvedic.dto.OrderSummaryDTO;
import com.techtricks.ayurvedic.exceptions.CartNotFoundException;
import com.techtricks.ayurvedic.exceptions.OrderNotFoundException;
import com.techtricks.ayurvedic.exceptions.UserNotFoundException;
import com.techtricks.ayurvedic.models.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService  {

    public OrderDTO placeOrder(Long userId, Long addressId) throws UserNotFoundException, CartNotFoundException;

    public List<OrderDTO> getOrdersByUser(Long userId);
 //   Optional<Order> updateOrderStatus(Long orderId, OrderStatus status);


    public OrderSummaryDTO getOrderSummary(Long orderId);

    public void updateOrderStatus(Long orderId, OrderStatus orderStatus) throws OrderNotFoundException;

    public List<OrderDTO> getAllOrders();
}
