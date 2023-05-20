package com.example.spring_project3.Controller;

import com.example.spring_project3.ApiResponse.ApiResponse;
import com.example.spring_project3.Model.Merchant;
import com.example.spring_project3.Model.Product;
import com.example.spring_project3.Service.CategoryService;
import com.example.spring_project3.Service.MerchantService;
import com.example.spring_project3.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getProduct(){
        ArrayList<Product> products = productService.getProducts();

        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
       if (checkCategory(product.getCategoryID())){
           productService.addProduct(product);
           return ResponseEntity.status(200).body("Product Added!");
       }
        return ResponseEntity.status(400).body(new ApiResponse("Sorry there is not any category with this id"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable int id ,@Valid @RequestBody Product product, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        boolean isUpdate = productService.updateProduct(id,product);

        if (isUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("Product update"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong id"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable int id){

        if (productService.deleteProduct(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Product Deleted :("));
        }
        return ResponseEntity.status(400).body("wrong id");

    }

    public boolean checkCategory(int categoryID){
        for (int i = 0; i < categoryService.getCategories().size(); i++) {
            if (categoryService.getCategories().get(i).getId() == categoryID){
                return true;
            }
        }
        return false;
    }
}
