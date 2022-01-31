package com.codecool.memonyx.repository;

import com.codecool.memonyx.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByName(String name);

    boolean existsByName(String name);
}
