package com.codecool.memonyx.payload.response;

import com.codecool.memonyx.entity.MeasuringUnit;
import com.codecool.memonyx.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private Float quantity;
    private MeasuringUnit measuringUnit;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.quantity = product.getQuantity();
        this.measuringUnit = product.getMeasuringUnit();
    }
}
