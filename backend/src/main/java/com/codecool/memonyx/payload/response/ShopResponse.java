package com.codecool.memonyx.payload.response;

import com.codecool.memonyx.entity.Product;
import com.codecool.memonyx.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopResponse {

    private Long id;
    private String name;
    private List<Product> products = new ArrayList<>();

    public ShopResponse(Shop shop) {
        this.id = shop.getId();
        this.name = shop.getName();
        this.products = shop.getProducts();
    }
}
