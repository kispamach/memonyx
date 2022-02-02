package com.codecool.memonyx.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserUpdateRequest {

    private Long id;
    private String firstName;
    private String lastName;
    private List<ShoppingRequest> shoppingRequestList;

}
