package com.example.spring_project3.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Category {

    @NotNull(message = " ID cannot be empty")
    @Min(value = 100, message = "ID size should be 3 or more ")
    private int id;

    @NotEmpty(message = " name cannot be empty")
    @Size(min = 3 , message = "name size should be 3 or more ")
    private String name;
}
