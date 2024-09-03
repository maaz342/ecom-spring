package com.maaz.Maaz.Ecommerce.repository;

import com.maaz.Maaz.Ecommerce.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
