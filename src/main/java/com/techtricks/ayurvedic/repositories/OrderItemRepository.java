package com.techtricks.ayurvedic.repositories;

import com.techtricks.ayurvedic.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository <OrderItem, Long> {
}
