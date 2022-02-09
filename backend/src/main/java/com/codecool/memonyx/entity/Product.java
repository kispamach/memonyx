package com.codecool.memonyx.entity;

import com.codecool.memonyx.payload.request.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Float quantity;

    @Enumerated(EnumType.STRING)
    private MeasuringUnit measuringUnit;

    public Product(ProductRequest productRequest) {
        this.setName(productRequest.getName());
        this.setQuantity(productRequest.getQuantity());
        this.setMeasuringUnit(productRequest.getMeasuringUnit());
    }
}
