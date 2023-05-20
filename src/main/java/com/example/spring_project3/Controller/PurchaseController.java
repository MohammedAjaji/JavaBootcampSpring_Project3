package com.example.spring_project3.Controller;

import com.example.spring_project3.ApiResponse.ApiResponse;
import com.example.spring_project3.Model.User;
import com.example.spring_project3.Service.MerchantStockService;
import com.example.spring_project3.Service.ProductService;
import com.example.spring_project3.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final ProductService productService;
    private final UserService userService;
    private final MerchantStockService merchantStockService;

    @PostMapping("/add")
    public ResponseEntity addPurchase(@RequestParam int userID,
                                      @RequestParam int productID,
                                      @RequestParam int merchantID){

        boolean user = false;
        boolean product = false;
        boolean merchant = false;

        int userIndex = 0;
        double userBalance = 0;

        int merchantIndex = 0;
        int merchantStock = 0;

        double productPrice = 0;


        for (int i = 0; i < userService.getUsers().size(); i++) {
            if (userID == userService.getUsers().get(i).getId()){
                user = true;
                userIndex = i;
                userBalance = userService.getUsers().get(i).getBalance();
            }
        }

        for (int i = 0; i < merchantStockService.getMerchantStocks().size(); i++) {
            if (merchantID == merchantStockService.getMerchantStocks().get(i).getMerchantID()
                    && productID == merchantStockService.getMerchantStocks().get(i).getProductID()){
                merchant = true;
                product = true;
                merchantIndex = i;
                merchantStock = merchantStockService.getMerchantStocks().get(i).getStock();
                if (merchantStock < 1){
                    return ResponseEntity.status(400).body(new ApiResponse("Sorry Product OUT OF STOCK"));
                }
            }
        }

        for (int i = 0; i < productService.getProducts().size(); i++) {
            if (productID == productService.getProducts().get(i).getId()){
                productPrice = productService.getProducts().get(i).getPrice();
            }
        }

        if (user && product && merchant){
            merchantStockService.getMerchantStocks().get(merchantIndex).setStock(merchantStock - 1);
            if (userBalance < productPrice){
                return ResponseEntity.status(400).body(new ApiResponse("Sorry user balance is less than product price"));
            }
            merchantStockService.getMerchantStocks().get(merchantIndex).setStock(merchantStock - 1);
            userService.getUsers().get(userIndex).setBalance(userBalance - productPrice);
            return ResponseEntity.status(200).body(new ApiResponse("Purchase has been completed"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("please check the userId, productId, or merchantID is wrong!!"));
    }


}
