package com.codecool.memonyx.service;


import com.codecool.memonyx.entity.Cart;
import com.codecool.memonyx.entity.Shop;
import com.codecool.memonyx.entity.Shopping;
import com.codecool.memonyx.entity.User;
import com.codecool.memonyx.exception.ShoppingNotFoundException;
import com.codecool.memonyx.payload.request.ShoppingRequest;
import com.codecool.memonyx.payload.response.MessageResponse;
import com.codecool.memonyx.repository.ShoppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ShoppingService {

    private ShoppingRepository shoppingRepository;
    private UserService userService;

    @Autowired
    public void setShoppingRepository(ShoppingRepository shoppingRepository) {
        this.shoppingRepository = shoppingRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Shopping findShopping(Long id) {
        return shoppingRepository.findShoppingById(id).orElseThrow(() -> new ShoppingNotFoundException(id));
    }

    public List<Shopping> findAllShopping() {
        return shoppingRepository.findAll();
    }

    @Transactional
    public Shopping addShopping(ShoppingRequest newShopping) {
        Shopping shopping = new Shopping();
        shopping.setDate(LocalDateTime.now());

        // Add shopping to user
        User user = userService.findUserById(newShopping.getUserId());
        List<Shopping> userShoppingList= user.getShoppingList();
        userShoppingList.add(shopping);
        user.setShoppingList(userShoppingList);
        return shoppingRepository.save(shopping);
    }

    @Transactional
    public Shopping updateShopping(Long id, ShoppingRequest newShopping) {
        Shopping shopping = findShopping(id);
        if (newShopping.getCarts() != null) shopping.setCarts(newShopping.getCarts()
                .stream()
                .map(Cart::new)
                .collect(Collectors.toList()));
        return shopping;
    }

    public ResponseEntity<?> deleteShopping(Long id) {
        shoppingRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Shopping deleted successfully: " + id));
    }
}
