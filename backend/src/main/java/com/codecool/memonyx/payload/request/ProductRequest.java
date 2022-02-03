package com.codecool.memonyx.payload.request;

import com.codecool.memonyx.entity.MeasuringUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductRequest {

    private Long id;
    private String name;
    private Float quantity;
    private MeasuringUnit measuringUnit;
    private Long shopId;
}
