package com.codecool.memonyx.controller;

import com.codecool.memonyx.entity.User;
import com.codecool.memonyx.payload.request.UserUpdateRequest;
import com.codecool.memonyx.payload.response.UserResponse;
import com.codecool.memonyx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/users/")
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
    public List<UserResponse> getAllUser() {
        return userService.findAllUser();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody UserUpdateRequest newUser, @PathVariable Long id){
        return userService.updateUser(id, newUser);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
