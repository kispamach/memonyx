package com.codecool.memonyx.payload.request;

import com.codecool.memonyx.entity.Shop;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ShoppingRequest {
    private Long id;
    private LocalDateTime date;
    private List<Shop> shops = new ArrayList<>();
}
