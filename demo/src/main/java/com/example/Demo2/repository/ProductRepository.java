package com.example.Demo2.repository;

import com.example.Demo2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Custom finder method to fetch all products of a specific category
    List<Product> findByCategory(String category);
}