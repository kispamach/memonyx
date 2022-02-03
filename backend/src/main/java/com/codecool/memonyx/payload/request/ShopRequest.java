package com.codecool.memonyx.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ShopRequest {

    private Long id;
    private String name;
    private List<ProductRequest> products;
    private Long shoppingId;
}
