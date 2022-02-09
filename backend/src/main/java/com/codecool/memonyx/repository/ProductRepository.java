package com.codecool.memonyx.repository;

import com.codecool.memonyx.entity.MeasuringUnit;
import com.codecool.memonyx.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByName(String name);

    boolean existsByNameAndQuantityAndMeasuringUnit(String name, Float quantity, MeasuringUnit measuringUnit);

    Optional<Product> findProductByNameAndQuantityAndMeasuringUnit(String name, Float quantity, MeasuringUnit measuringUnit);
}
