package com.example.spring_project3.Model;

import com.example.spring_project3.Service.CategoryService;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Product {

//    private final Category category = getCategory();

    @NotNull(message = " ID cannot be empty")
    @Min(value = 100, message = "ID size should be 3 or more ")
    private int id;

    @NotEmpty(message = " name cannot be empty")
    @Size(min = 3 , message = "name size should be 3 or more ")
    private String name;

    @NotNull(message = " price cannot be empty")
    @Positive(message = " price cannot be negative")
    private double price;

//    @NotNull(message = " categoryID cannot be empty")
//    @Min(value = 100, message = "categoryID size should be 3 or more ")

    private int categoryID;


}
