package com.codecool.memonyx.repository;

import com.codecool.memonyx.entity.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingRepository extends JpaRepository<Shopping, Long> {
}
