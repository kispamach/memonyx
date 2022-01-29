package com.codecool.memonyx.service;


import com.codecool.memonyx.entity.User;
import com.codecool.memonyx.payload.request.UserUpdateRequest;
import com.codecool.memonyx.payload.response.UserResponse;
import com.codecool.memonyx.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return ResponseEntity.ok(new UserResponse(user));
    }

    public ResponseEntity<?> findAllUser() {
        return ResponseEntity.ok(userRepository.findAll()
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList()));
    }

    @Transactional
    public ResponseEntity<?> updateUser(Long id, UserUpdateRequest newUser) {
        User user = userRepository.findUserById(id).orElseThrow(() ->new UserNotFoundException(id));
        if (newUser.getFirstName() != null) user.setFirstName(newUser.getFirstName());
        if (newUser.getLastName() != null) user.setLastName(newUser.getLastName());
        return ResponseEntity.ok(new UserResponse(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /** Converts a User into a UserResponse */
    private ResponseEntity<UserResponse> convertToUserResponse(User user) {
        return ResponseEntity.ok(new UserResponse(user));
    }

//    /** Converts a list of Users into a list of UserDTOs */
//    private List<ResponseEntity<UserResponse>> convertToUserResponseList(List<User> users) {
//        return users.stream()
//                .map(UserResponse::new)
//                .collect(Collectors.toList());
//    }
}
