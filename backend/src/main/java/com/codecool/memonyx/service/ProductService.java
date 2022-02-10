package com.codecool.memonyx.service;

import com.codecool.memonyx.entity.Product;
import com.codecool.memonyx.entity.Shop;
import com.codecool.memonyx.exception.ProductNotFoundException;
import com.codecool.memonyx.payload.request.ProductRequest;
import com.codecool.memonyx.payload.response.MessageResponse;
import com.codecool.memonyx.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product findProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> findAllProduct() {
        return productRepository.findAll();

    }

    @Transactional
    public Product addProduct(ProductRequest newProduct) {
        if (productRepository.existsByNameAndQuantityAndMeasuringUnit(newProduct.getName(),
                newProduct.getQuantity(),
                newProduct.getMeasuringUnit())) {
            Product product = productRepository.findProductByNameAndQuantityAndMeasuringUnit(newProduct.getName(),
                            newProduct.getQuantity(),
                            newProduct.getMeasuringUnit())
                    .orElse(null);

            // Add product to shop's product list
            addProductToShop(product, newProduct.getShopId());
            return product;
        }
        Product product = new Product();
        product.setName(newProduct.getName());
        product.setQuantity(newProduct.getQuantity());
        product.setMeasuringUnit(newProduct.getMeasuringUnit());

        // Add product to shop's product list
        addProductToShop(product, newProduct.getShopId());

        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, ProductRequest newProduct) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        if (newProduct.getName() != null) product.setName(newProduct.getName());
        if (newProduct.getQuantity() != null) product.setQuantity(newProduct.getQuantity());
        if (newProduct.getMeasuringUnit() != null) product.setMeasuringUnit(newProduct.getMeasuringUnit());
        return product;
    }

    public ResponseEntity<?> deleteProduct(Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Product deleted successfully: " + id));
    }

    /** Add product to shop's product list */
    private void addProductToShop(Product product, Long shopId) {
        Shop shop = shopService.findShop(shopId);
        System.out.println(shop.getName());
        List<Product> shopProducts = shop.getProducts();
        shopProducts.add(product);
        shop.setProducts(shopProducts);
    }
}
