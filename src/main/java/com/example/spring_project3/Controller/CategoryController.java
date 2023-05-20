package com.example.spring_project3.Controller;

import com.example.spring_project3.ApiResponse.ApiResponse;
import com.example.spring_project3.Model.Category;
import com.example.spring_project3.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getCategories(){
        ArrayList<Category> categories = categoryService.getCategories();

        return ResponseEntity.status(200).body(categories);
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body("Category Added!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable int id ,@Valid @RequestBody Category category, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        boolean isUpdate = categoryService.updateCategory(id,category);

        if (isUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("Category update"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong id"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable int id){

        if (categoryService.deleteCategory(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Category Deleted :("));
        }
        return ResponseEntity.status(400).body("wrong id");

    }
}
