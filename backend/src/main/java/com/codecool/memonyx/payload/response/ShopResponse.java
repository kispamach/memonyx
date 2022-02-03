package com.codecool.memonyx.payload.response;

import com.codecool.memonyx.controller.ProductController;
import com.codecool.memonyx.controller.ShopController;
import com.codecool.memonyx.entity.Shop;
import com.codecool.memonyx.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopResponse {

    private Long id;
    private String name;
    private List<String> products;
    private String url;

    public ShopResponse(Shop shop) {
        this.id = shop.getId();
        this.name = shop.getName();
        this.products = shop.getProducts()
                .stream()
                .map(product -> Utils.urlCreator(ShopController.class, product.getId()))
                .collect(Collectors.toList());
        this.url = Utils.urlCreator(ShopController.class, id);
    }
}
