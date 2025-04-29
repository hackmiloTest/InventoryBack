package com.hack.InventoryManagementSystem.repository;

import com.hack.InventoryManagementSystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySku(String sku);
}
