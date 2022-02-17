package com.codecool.memonyx.service;

import com.codecool.memonyx.controller.ShoppingController;
import com.codecool.memonyx.controller.UserController;
import com.codecool.memonyx.entity.Shopping;
import com.codecool.memonyx.entity.User;
import com.codecool.memonyx.exception.UserNotFoundException;
import com.codecool.memonyx.payload.request.UserUpdateRequest;
import com.codecool.memonyx.payload.response.MessageResponse;
import com.codecool.memonyx.payload.response.UserResponse;
import com.codecool.memonyx.repository.UserRepository;
import com.codecool.memonyx.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    private UserRepository userRepository;
    private Utils utils;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUtils(Utils utils) {
        this.utils = utils;
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id).orElseThrow(() ->new UserNotFoundException(id));
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Transactional
    public User updateUser(Long id, UserUpdateRequest newUser) {
        User user = userRepository.findUserById(id).orElseThrow(() ->new UserNotFoundException(id));
        if (newUser.getFirstName() != null) user.setFirstName(newUser.getFirstName());
        if (newUser.getLastName() != null) user.setLastName(newUser.getLastName());
        if (newUser.getShoppingRequestList() != null) user.setShoppingList(newUser.getShoppingRequestList()
                .stream()
                .map(Shopping::new)
                .collect(Collectors.toList()));
        return user;
    }

    public ResponseEntity<?> deleteUser(Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Shop deleted successfully: " + id));
    }

    public UserResponse userConvertToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getShoppingList()
                        .stream()
                        .map(shopping -> utils.urlCreator(ShoppingController.class, shopping.getId()))
                        .collect(Collectors.toList()),
                utils.urlCreator(UserController.class, user.getId())
        );
    }
}
