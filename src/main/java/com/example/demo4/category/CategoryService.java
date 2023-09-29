package com.example.demo4.category;

import com.example.demo4.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }
}
