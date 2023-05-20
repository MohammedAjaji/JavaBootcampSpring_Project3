package com.example.spring_project3.Controller;


import com.example.spring_project3.ApiResponse.ApiResponse;
import com.example.spring_project3.Model.MerchantStock;
import com.example.spring_project3.Service.MerchantService;
import com.example.spring_project3.Service.MerchantStockService;
import com.example.spring_project3.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/merchant-stock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;
    private final ProductService productService;
    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getUser(){
        ArrayList<MerchantStock> merchantStocks = merchantStockService.getMerchantStocks();

        return ResponseEntity.status(200).body(merchantStocks);
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        if (checkProduct(merchantStock.getProductID()) && checkMerchant(merchantStock.getMerchantID())){
            merchantStockService.addMerchantStock(merchantStock);
            return ResponseEntity.status(200).body("Stack Added!");
        }

        return ResponseEntity.status(400).body(new ApiResponse("Sorry there is not any product or merchant  with this id"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable int id ,@Valid @RequestBody MerchantStock merchantStock, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        boolean isUpdate = merchantStockService.updateMerchantStock(id,merchantStock);

        if (isUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("Stack updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong id"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable int id){

        if (merchantStockService.deleteMerchantStock(id)){
            return ResponseEntity.status(200).body(new ApiResponse("User Deleted :("));
        }
        return ResponseEntity.status(400).body("wrong id");

    }

    public boolean checkProduct(int productID){
        for (int i = 0; i < productService.getProducts().size(); i++) {
            if (productService.getProducts().get(i).getId() == productID){
                return true;
            }
        }
        return false;
    }
    public boolean checkMerchant(int merchantID){
        for (int i = 0; i < merchantService.getMerchants().size(); i++) {
            if (merchantService.getMerchants().get(i).getId() == merchantID){
                return true;
            }
        }
        return false;
    }


    @PutMapping("/addProductToMerchantStock/{productID}/{merchantID}/{stock}")
    public ResponseEntity addProductToMerchantStock(@Valid @PathVariable int productID,
                                                    @Valid @PathVariable int merchantID,
                                                    @Valid @PathVariable int stock,
                                                    @Valid @RequestBody MerchantStock merchantStock,
                                                    Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean productAdded = merchantStockService.addProductToMerchantStock(productID,merchantID,stock, merchantStock);

        if (productAdded){
            return ResponseEntity.status(200).body(new ApiResponse("Product added to Merchant Stock"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Sorry check the merchant ID"));
    }

}
