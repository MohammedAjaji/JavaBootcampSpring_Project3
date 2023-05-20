package com.example.spring_project3.Service;

import com.example.spring_project3.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {

    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    public ArrayList<MerchantStock> getMerchantStocks(){
        return merchantStocks;
    }

    public void addMerchantStock(MerchantStock merchantStock){
        merchantStocks.add(merchantStock);
    }

    public boolean updateMerchantStock(int id, MerchantStock merchantStock){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if(merchantStocks.get(i).getId() == id){
                merchantStocks.set(i,merchantStock);
                return true;
            }
        }
        return false;
    }


    public boolean deleteMerchantStock(int id){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if(merchantStocks.get(i).getId() == id){
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean addProductToMerchantStock(int productID, int merchantID, int stock, MerchantStock merchantStock ){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getMerchantID() == merchantID) {
                for (int j = 0; j < merchantStocks.size(); j++) {
                    if (merchantStocks.get(j).getProductID() == productID && merchantStocks.get(j).getMerchantID() == merchantID) {
                        merchantStocks.get(j).setStock(stock);
                        return true;
                    }
                }
                merchantStocks.add(merchantStock);
                return true;
            }
        }
        return false;
    }



}
