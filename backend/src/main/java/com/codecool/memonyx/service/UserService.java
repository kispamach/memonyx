package com.codecool.memonyx.service;


import com.codecool.memonyx.entity.User;
import com.codecool.memonyx.payload.request.UserUpdateRequest;
import com.codecool.memonyx.payload.response.UserResponse;
import com.codecool.memonyx.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> findUserById(Long id) {
        User user = userRepository.findUserById(id).orElseThrow(() ->new UserNotFoundException(id));
        return ResponseEntity.ok(new UserResponse(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName()));
    }

    public List<UserResponse> findAllUser() {
        return this.convertToUserResponseList(userRepository.findAll());
    }

    @Transactional
    public ResponseEntity<?> updateUser(Long id, UserUpdateRequest newUser) {
        User user = userRepository.findUserById(id).orElseThrow(() ->new UserNotFoundException(id));
        if (newUser.getFirstName() != null) user.setFirstName(newUser.getFirstName());
        if (newUser.getLastName() != null) user.setLastName(newUser.getLastName());
        return ResponseEntity.ok(new UserResponse(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName()));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
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
