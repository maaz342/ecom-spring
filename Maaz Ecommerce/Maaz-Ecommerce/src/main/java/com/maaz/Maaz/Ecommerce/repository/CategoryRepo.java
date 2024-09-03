package com.maaz.Maaz.Ecommerce.repository;

import com.maaz.Maaz.Ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
}
