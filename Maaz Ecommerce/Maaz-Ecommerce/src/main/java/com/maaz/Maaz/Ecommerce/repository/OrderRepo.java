package com.maaz.Maaz.Ecommerce.repository;

import com.maaz.Maaz.Ecommerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderItem,Long> {
}
