package com.codecool.memonyx.payload.response;

import com.codecool.memonyx.entity.Shop;
import com.codecool.memonyx.entity.Shopping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ShoppingResponse {

    private Long id;
    private LocalDateTime date;
    private List<Shop> shopList;

    public ShoppingResponse(Shopping shopping) {
        this.id = shopping.getId();
        this.date = shopping.getDate();
        this.shopList = shopping.getShops();
    }
}
