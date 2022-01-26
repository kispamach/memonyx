package com.codecool.memonyx.service;

import com.codecool.memonyx.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ProductService {

    private ProductRepository productRepository;

}
