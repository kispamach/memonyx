package com.codecool.memonyx.payload.response;

import com.codecool.memonyx.controller.CartController;
import com.codecool.memonyx.controller.ProductController;
import com.codecool.memonyx.controller.ShopController;
import com.codecool.memonyx.controller.ShoppingController;
import com.codecool.memonyx.entity.Cart;
import com.codecool.memonyx.entity.Shopping;
import com.codecool.memonyx.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class CartResponse {
    private Long id;
    private LocalDateTime date;
    private String shop;
    private List<String> products;
    private String url;

    public CartResponse(Cart cart) {
        this.id = cart.getId();
        this.date = cart.getDate();
        this.shop = cart.getShop() == null
                ? null
                : Utils.urlCreator(ShopController.class, cart.getShop().getId());
        this.products = cart.getProducts()
                    .stream()
                    .map(product -> Utils.urlCreator(ProductController.class, product.getId()))
                    .collect(Collectors.toList());
        this.url = Utils.urlCreator(CartController.class, id);
    }
}
