package com.example.demo4.product;

import com.example.demo4.helpers.ResponseHandler;
import com.example.demo4.product.dto.ProductPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/product")
public class ProductController {

    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getProduct() {
        try {
            List<Product> products = this.productService.getProduct();
            return ResponseHandler.generateResponse("Success get products", HttpStatus.OK, products);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<Object> detailProduct(@PathVariable("id") Long id) {
        try {
            Optional<Product> product = this.productService.detailProduct(id);
            return ResponseHandler.generateResponse("Success get detail product", HttpStatus.OK, product);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createProduct(@RequestBody @Valid ProductPayload product) {
        try {
            this.productService.createProduct(product);
            return ResponseHandler.generateResponse("Success create product", HttpStatus.OK, product);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") Long id, @RequestBody ProductPayload product) {
        try {
            this.productService.updateProduct(id, product);
            return ResponseHandler.generateResponse("Success update product", HttpStatus.OK, product);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id) {
        try {
            this.productService.deleteProduct(id);
            return ResponseHandler.generateResponse("Success delete product", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
