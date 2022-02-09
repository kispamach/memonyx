package com.codecool.memonyx.repository;

import com.codecool.memonyx.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findCartById(Long id);
}
