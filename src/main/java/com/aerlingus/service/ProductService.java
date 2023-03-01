package com.aerlingus.service;

import com.aerlingus.model.Product;
import com.aerlingus.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final Random random = new Random();

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Product existingProduct, Product product) {
        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductDescription(product.getProductDescription());
        existingProduct.setProductPrice(product.getProductPrice());
        existingProduct.setProductStock(product.getProductStock());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long productId) {
        productRepository.findById(productId).ifPresentOrElse(productRepository::delete, () -> {
            throw new ResponseStatusException(NOT_FOUND, "Product not found");
        });
    }

    public Iterable<Product> createProducts(int amount) {
        return productRepository.saveAll(IntStream.range(0, amount).mapToObj(i -> Product.builder()
                .productName("productName_" + i)
                .productDescription("productDescription_" + i)
                .productPrice(Math.round(random.nextFloat() * 9999.99f) / 100.0f)
                .productStock(random.nextInt(1000) +1)
                .build()).toList());
    }

    public void deleteAllProducts() {
        productRepository.deleteAll();
    }
}
