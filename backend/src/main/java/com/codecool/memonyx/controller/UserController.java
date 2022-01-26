package com.codecool.memonyx.controller;

import com.codecool.memonyx.payload.response.UserResponse;
import com.codecool.memonyx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/user/")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("")
    public List<UserResponse> getAllUsers() {
        return userService.findAllUsers();
    }



}
