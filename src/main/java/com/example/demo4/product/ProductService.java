package com.example.demo4.product;

import com.example.demo4.category.Category;
import com.example.demo4.category.CategoryRepository;
import com.example.demo4.category.CategoryService;
import com.example.demo4.product.dto.ProductPayload;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    private String generateRandomString(int length) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {

            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    private String generateProductCode(String code) {
        if (code.isEmpty()) {
            code = generateRandomString(5);
        }
        String baseCode = code.replaceAll("\\s+", "").toUpperCase();
        String generatedCode = baseCode;
        int suffix = 1;

        while (productRepository.existsByCode(generatedCode)) {
            generatedCode = baseCode + suffix;
            suffix++;
        }

        return generatedCode;
    }

    public List<Product> getProduct() {
        return this.productRepository.findAll();
    }
    public Optional<Product> detailProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new IllegalStateException("Product not found");
        }

        return product;
    }

    public Product createProduct(ProductPayload data) {

        Long category_id = data.getCategory_id();
        Category category = categoryRepository.findById(category_id).orElseThrow(() -> new IllegalStateException("Category not found"));

        Product product = new Product();
        product.setName(data.getName());
        product.setCode(generateProductCode(data.getCode()));
        product.setPrice(data.getPrice());
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }

    public Product updateProduct(Long id, ProductPayload data) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalStateException("Product not found"));

        Long category_id = data.getCategory_id();
        Category category = categoryRepository.findById(category_id).orElseThrow(() -> new IllegalStateException("Category not found"));

        if (!data.getCode().equals(product.getCode())) {
            product.setCode(generateProductCode(data.getCode()));
        }
        product.setName(data.getName());
        product.setPrice(data.getPrice());
        product.setCategory(category);
        productRepository.save(product);

        return product;
    }

    public void deleteProduct(Long id) {
        boolean exists = productRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Product not found");
        }

        productRepository.deleteById(id);
    }

}
