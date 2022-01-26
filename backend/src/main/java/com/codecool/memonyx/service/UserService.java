package com.codecool.memonyx.service;


import com.codecool.memonyx.entity.User;
import com.codecool.memonyx.payload.response.MessageResponse;
import com.codecool.memonyx.payload.response.UserResponse;
import com.codecool.memonyx.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> findUserById(Long id) {
        User user = userRepository.findUserById(id).orElse(null);
        if (user == null) return ResponseEntity.badRequest().body(new MessageResponse("User not found!"));
        return ResponseEntity.ok(new UserResponse(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName()));
    }

    public List<UserResponse> findAllUsers() {
        return this.convertToUserResponseList(userRepository.findAll());
    }

    /** Converts a User into a UserResponse */
    private UserResponse convertToUserResponse(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName());
    }

    /** Converts a list of Users into a list of UserDTOs */
    private List<UserResponse> convertToUserResponseList(List<User> users) {
        return users.stream()
                .map(this::convertToUserResponse)
                .collect(Collectors.toList());
    }
}
