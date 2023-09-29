package com.example.demo4.category;

import com.example.demo4.helpers.ResponseHandler;
import com.example.demo4.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/get")
    public ResponseEntity<Object> getCategory() {
        try {
            List<Category> categories = this.categoryService.getCategory();
            return ResponseHandler.generateResponse("Success get categories", HttpStatus.OK, categories);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCategory(@RequestBody @Valid Category category) {
        try {
            categoryService.createCategory(category);
            return ResponseHandler.generateResponse("Success create category", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
