package com.example.spring_project3.Controller;


import com.example.spring_project3.ApiResponse.ApiResponse;
import com.example.spring_project3.Model.Product;
import com.example.spring_project3.Model.User;
import com.example.spring_project3.Service.ProductService;
import com.example.spring_project3.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUser(){
        ArrayList<User> users = userService.getUsers();

        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        userService.addUser(user);

        return ResponseEntity.status(200).body("User Added!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable int id ,@Valid @RequestBody User user, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        boolean isUpdate = userService.updateUser(id,user);

        if (isUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("User update"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong id"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){

        if (userService.deleteUser(id)){
            return ResponseEntity.status(200).body(new ApiResponse("User Deleted :("));
        }
        return ResponseEntity.status(400).body("wrong id");

    }
}
