package com.codecool.memonyx.repository;

import com.codecool.memonyx.entity.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ShoppingRepository extends JpaRepository<Shopping, Long> {

    Optional<Shopping> findShoppingById(Long id);
}
