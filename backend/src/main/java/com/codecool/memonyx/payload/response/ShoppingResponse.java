package com.codecool.memonyx.payload.response;

import com.codecool.memonyx.controller.CartController;
import com.codecool.memonyx.controller.ShoppingController;
import com.codecool.memonyx.entity.Shopping;
import com.codecool.memonyx.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class ShoppingResponse {

    private Long id;
    private LocalDateTime date;
    private List<String> carts;
    private String url;

    public ShoppingResponse(Shopping shopping) {
        this.id = shopping.getId();
        this.date = shopping.getDate();
        this.carts = shopping.getCarts()
                .stream()
                .map(cart -> Utils.urlCreator(CartController.class, cart.getId()))
                .collect(Collectors.toList());
        this.url = Utils.urlCreator(ShoppingController.class, id);
    }
}
