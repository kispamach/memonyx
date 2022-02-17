package com.codecool.memonyx.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingResponse {

    private Long id;
    private LocalDateTime date;
    private List<String> carts;
    private String url;
}
