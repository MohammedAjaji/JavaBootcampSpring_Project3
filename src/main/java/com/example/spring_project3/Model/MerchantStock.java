package com.example.spring_project3.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MerchantStock{


    @NotNull(message = " ID cannot be empty")
    @Min(value = 100, message = "ID size should be 3 or more ")
    private int id;

    @NotNull(message = " productID cannot be empty")
    @Min(value = 100, message = "productID size should be 3 or more ")
    private int productID;

    @NotNull(message = " merchantID cannot be empty")
    @Min(value = 100, message = "merchantID size should be 3 or more ")
    private int merchantID;

    @NotNull(message = " stock cannot be empty")
    @Min(value = 11, message = "stock have to be more than 10")
    private int stock;

}
