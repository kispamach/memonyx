package com.codecool.memonyx.service;


import com.codecool.memonyx.repository.ShoppingRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ShoppingService {

    private ShoppingRepository shoppingRepository;
}
