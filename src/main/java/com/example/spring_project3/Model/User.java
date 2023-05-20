package com.example.spring_project3.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@AllArgsConstructor
@Data
public class User {

    @NotNull(message = " ID cannot be empty")
    @Min(value = 100, message = "ID size should be 3 or more ")
    private int id;

    @NotEmpty(message = " username cannot be empty")
    @Size(min = 5 , message = "username size should be 5 or more ")
    private String username;

    @NotEmpty(message = " password cannot be empty")
    @Size(min = 6 , message = "password size should be 6 or more ")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$",
            message = "password must have characters and digits ")
    private String password;

    @NotEmpty(message = " email cannot be empty")
    @Email(message = "it has to be a valid email")
    private String email;

    @NotEmpty(message = " role cannot be empty")
    @Pattern(regexp = "^(Admin|Customer)$", message = "role must be Admin or Customer only")
    private String role;

    @NotNull(message = " balance cannot be empty")
    @Positive(message = " balance cannot be negative")
    private double balance;
}
