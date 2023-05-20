package com.example.spring_project3.Controller;

import com.example.spring_project3.ApiResponse.ApiResponse;
import com.example.spring_project3.Model.Category;
import com.example.spring_project3.Model.Merchant;
import com.example.spring_project3.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getMerchants(){
        ArrayList<Merchant> merchants = merchantService.getMerchants();

        return ResponseEntity.status(200).body(merchants);
    }

    @PostMapping("/add")
    public ResponseEntity addMerchant(@Valid @RequestBody Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        merchantService.addMerchant(merchant);

        return ResponseEntity.status(200).body("Merchant Added!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable int id ,@Valid @RequestBody Merchant merchant, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        boolean isUpdate = merchantService.updateMerchant(id,merchant);

        if (isUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("Merchant update"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong id"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable int id){

        if (merchantService.deleteMerchant(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Deleted :("));
        }
        return ResponseEntity.status(400).body("wrong id");

    }

}
