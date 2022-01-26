package com.codecool.memonyx.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
}
