package com.codecool.memonyx.service;

import com.codecool.memonyx.controller.ProductController;
import com.codecool.memonyx.entity.Cart;
import com.codecool.memonyx.entity.Product;
import com.codecool.memonyx.exception.ProductNotFoundException;
import com.codecool.memonyx.payload.request.ProductRequest;
import com.codecool.memonyx.payload.response.MessageResponse;
import com.codecool.memonyx.payload.response.ProductResponse;
import com.codecool.memonyx.repository.ProductRepository;
import com.codecool.memonyx.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductService {

    private ProductRepository productRepository;
    private CartService cartService;
    private Utils utils;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @Autowired
    public void setUtils(Utils utils) {
        this.utils = utils;
    }

    public Product findProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> findAllProduct() {
        return productRepository.findAll();

    }

    @Transactional
    public Product addProduct(ProductRequest newProduct) {
        //Checks the existence of the product
        if (productRepository.existsByNameIgnoreCaseAndQuantityAndMeasuringUnit(newProduct.getName(),
                newProduct.getQuantity(),
                newProduct.getMeasuringUnit())) {
            Product product = productRepository.findProductByNameIgnoreCaseAndQuantityAndMeasuringUnit(
                            newProduct.getName(),
                            newProduct.getQuantity(),
                            newProduct.getMeasuringUnit())
                    .orElse(null);

            // Add product to cart's product list
            addProductToCart(product, newProduct.getCartId());
            return product;
        }
        Product product = new Product();
        product.setName(newProduct.getName());
        product.setQuantity(newProduct.getQuantity());
        product.setMeasuringUnit(newProduct.getMeasuringUnit());

        // Add product to cart's product list
        addProductToCart(product, newProduct.getCartId());

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
    private void addProductToCart(Product product, Long cartId) {
        Cart cart = cartService.findCartById(cartId);
        List<Product> cartProducts = cart.getProducts();
        cartProducts.add(product);
        cart.setProducts(cartProducts);
    }

    public ProductResponse productConvertToProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getQuantity(),
                product.getMeasuringUnit(),
                utils.urlCreator(ProductController.class, product.getId())
        );
    }
}
