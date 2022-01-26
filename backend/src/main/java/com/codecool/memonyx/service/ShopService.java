package com.codecool.memonyx.service;


import com.codecool.memonyx.repository.ShopRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ShopService {

    private ShopRepository shopRepository;
}
