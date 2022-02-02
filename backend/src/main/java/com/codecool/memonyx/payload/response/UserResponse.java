package com.codecool.memonyx.payload.response;

import com.codecool.memonyx.entity.User;
import com.codecool.memonyx.payload.request.ShoppingRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class UserResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private List<ShoppingResponse> shoppingResponseList;

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.shoppingResponseList = user.getShoppingList()
                .stream()
                .map(ShoppingResponse::new)
                .collect(Collectors.toList());
    }
}
