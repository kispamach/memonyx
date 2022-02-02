package com.codecool.memonyx.payload.response;

import com.codecool.memonyx.entity.Product;
import com.codecool.memonyx.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopResponse {

    private Long id;
    private String name;
    private List<ProductResponse> products = new ArrayList<>();

    public ShopResponse(Shop shop) {
        this.id = shop.getId();
        this.name = shop.getName();
        this.products = shop.getProducts()
                .stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }
}
