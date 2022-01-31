package com.codecool.memonyx.payload.request;

import com.codecool.memonyx.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ShopRequest {

    private Long id;
    private String name;
    private List<Product> products;
}
