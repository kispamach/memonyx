package com.codecool.memonyx.payload.response;

import com.codecool.memonyx.entity.MeasuringUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private Float quantity;
    private MeasuringUnit measuringUnit;
    private String url;
}
