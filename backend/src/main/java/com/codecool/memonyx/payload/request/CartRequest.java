package com.codecool.memonyx.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CartRequest {
    private Long id;
    private LocalDateTime date;
    private ShopRequest shop;
    private List<ProductRequest> productIds;
}
