package com.codecool.memonyx.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ShopRequest {

    private Long id;
    private String name;
    private Long cartId;
}
