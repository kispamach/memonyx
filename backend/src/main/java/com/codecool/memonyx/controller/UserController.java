package com.codecool.memonyx.controller;

import com.codecool.memonyx.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@NoArgsConstructor
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/user/")
public class UserController {

    private UserService userService;


    @GetMapping("profile/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }
}
