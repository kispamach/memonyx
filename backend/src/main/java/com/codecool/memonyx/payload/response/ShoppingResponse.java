package com.codecool.memonyx.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ShoppingResponse {

    private Long id;
    private List<Long> ShopList;
}
