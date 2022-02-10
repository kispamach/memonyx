package com.codecool.memonyx.payload.response;

import com.codecool.memonyx.controller.ShopController;
import com.codecool.memonyx.entity.Shop;
import com.codecool.memonyx.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopResponse {

    private Long id;
    private String name;
    private String url;

    public ShopResponse(Shop shop) {
        this.id = shop.getId();
        this.name = shop.getName();
        this.url = Utils.urlCreator(ShopController.class, id);
    }
}
