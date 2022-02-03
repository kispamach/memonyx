package com.codecool.memonyx.payload.response;

import com.codecool.memonyx.controller.ShopController;
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
    private List<String> shops;
    private String url;

    public ShoppingResponse(Shopping shopping) {
        this.id = shopping.getId();
        this.date = shopping.getDate();
        this.shops = shopping.getShops()
                .stream()
                .map(product -> Utils.urlCreator(ShopController.class, product.getId()))
                .collect(Collectors.toList());
        this.url = Utils.urlCreator(ShoppingController.class, id);
    }
}
