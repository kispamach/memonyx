package com.codecool.memonyx.entity;

import com.codecool.memonyx.payload.request.ShopRequest;
import com.codecool.memonyx.payload.request.ShoppingRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private List<Product> products = new ArrayList<>();


    public Shop(ShopRequest newShop) {
        this.setName(newShop.getName());
        this.setProducts(newShop.getProducts()
                .stream()
                .map(Product::new)
                .collect(Collectors.toList()));
    }
}
