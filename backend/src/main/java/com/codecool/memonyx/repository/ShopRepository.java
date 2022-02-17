package com.codecool.memonyx.repository;

import com.codecool.memonyx.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ShopRepository extends JpaRepository<Shop, Long> {

    Optional<Shop> findShopById(Long id);

    boolean existsByNameIgnoreCase(String name);

    Optional<Shop> findShopByNameIgnoreCase(String name);

}
