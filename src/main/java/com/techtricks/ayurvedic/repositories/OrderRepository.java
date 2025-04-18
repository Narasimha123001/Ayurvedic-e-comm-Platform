package com.techtricks.ayurvedic.repositories;

import com.techtricks.ayurvedic.models.Order;
import com.techtricks.ayurvedic.models.OrderStatus;
import com.techtricks.ayurvedic.models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser(User user);
    List<Order> findByUserId(Long userId);
    List<Order> findByStatus(OrderStatus status);
    Optional<Order> findById(Long orderId);

    @EntityGraph(attributePaths = {"orderItems"})
    @Query("SELECT o From Order o JOIN FETCH o.orderItems where  o.user.id = :userId")
    List<Order> findByUserIdWithOrderItems(@Param("userId") Long userId);

    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.orderItems")
    List<Order> findAllWithOrderItems();

}
