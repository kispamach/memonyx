package com.codecool.memonyx.controller;

import com.codecool.memonyx.payload.request.UserUpdateRequest;
import com.codecool.memonyx.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/users/")
public class UserController {

    private final UserService userService;


    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.userConvertToUserResponse(userService.findUserById(id)));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok(userService.findAllUser()
                .stream()
                .map(userService::userConvertToUserResponse)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody UserUpdateRequest newUser, @PathVariable Long id){
        return ResponseEntity.ok(userService.userConvertToUserResponse(userService.updateUser(id, newUser)));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
