package com.codecool.memonyx.payload.response;

import com.codecool.memonyx.controller.ProductController;
import com.codecool.memonyx.controller.ShoppingController;
import com.codecool.memonyx.controller.UserController;
import com.codecool.memonyx.entity.User;
import com.codecool.memonyx.util.Utils;
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
    private List<String> shoppingList;
    private String url;

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.shoppingList = user.getShoppingList()
                .stream()
                .map(product -> Utils.urlCreator(UserController.class, product.getId()))
                .collect(Collectors.toList());
        this.url = Utils.urlCreator(UserController.class, id);
    }
}
