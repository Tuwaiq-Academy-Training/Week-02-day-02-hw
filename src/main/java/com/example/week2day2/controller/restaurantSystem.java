package com.example.week2day2.controller;


import com.example.week2day2.model.Food;
import com.example.week2day2.model.ResponseApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
public class restaurantSystem {
    private ArrayList<Food> foodlist=new ArrayList();

    @GetMapping("/food")
    public ResponseEntity getFood(){
    return ResponseEntity.status(200).body(foodlist);
 }
   @PostMapping("/food")
    public ResponseEntity addFood(@RequestBody Food food){
       foodlist.add(food);
       return ResponseEntity.status(201).body(new ResponseApi("Food added successfully"));
   }
   @PutMapping("/food/{index}")
    public ResponseEntity updateFood(@PathVariable Integer index, @RequestBody Food food){
       if(index>foodlist.size()-1 || index<0){
           return ResponseEntity.status(400).body(new ResponseApi("you have entered an invalid index :"+index));
       }
       foodlist.set(index,food);
       return ResponseEntity.status(201).body(new ResponseApi("Food has been successfully updated "));
   }
  @DeleteMapping("/food/{index}")

  public ResponseEntity deleteFood(@PathVariable Integer index){

      if(index>foodlist.size()-1 || index<0){
          return ResponseEntity.status(400).body(new ResponseApi("you have entered an invalid index :"+index));
      }
      foodlist.remove((int) index);
      return ResponseEntity.status(200).body(new ResponseApi("Food has been successfully deleted"));
  }
  @PutMapping("/food/addqty/{id}")
    public ResponseEntity addQuantity(@PathVariable Integer id, @RequestBody Integer moreQty){

        boolean isFound=false;
        for(int i=0;i<foodlist.size();i++){
            if(foodlist.get(i).getId()==id){
                isFound=true;
                foodlist.get(i).setQuantity(foodlist.get(i).getQuantity()+moreQty);

            }
        }
        if(isFound==false){
            return ResponseEntity.status(400).body(new ResponseApi("Item not found"));
        }

        return ResponseEntity.status(201).body(new ResponseApi("Quantity has been successfully added "));
    }
    @PutMapping("/food/rmqty/{id}")
    public ResponseEntity removeQuantity(@PathVariable Integer id, @RequestBody Integer Qty){

        boolean isFound=false;
        for(int i=0;i<foodlist.size();i++){
            if(foodlist.get(i).getId()==id){
                isFound=true;
                if(foodlist.get(i).getQuantity()-Qty<0){
                    return ResponseEntity.status(400).body(new ResponseApi("Removing more than available quantity"));
                }
                foodlist.get(i).setQuantity(foodlist.get(i).getQuantity()-Qty);

            }
        }
        if(isFound==false){
            return ResponseEntity.status(400).body(new ResponseApi("Item not found"));
        }

        return ResponseEntity.status(201).body(new ResponseApi("Quantity has been successfully removed "));
    }
    @GetMapping("/food/expiry/{id}")
    public ResponseEntity checkExpiry(@PathVariable Integer id){
        boolean isFound=false;
        boolean isExpired=false;
        int item_index=0;
        for(int i=0;i<foodlist.size();i++){
            if(foodlist.get(i).getId()==id){
                isFound=true;
                if(LocalDate.now().isAfter(foodlist.get(i).getExpirydate())){
                    isExpired=true;
                }
                item_index=i;
            }
        }
        if(isFound==false){
            return ResponseEntity.status(400).body(new ResponseApi("Item not found"));
        }

        if(isExpired){
            return ResponseEntity.status(200).body(new ResponseApi(foodlist.get(item_index).getName()+" is Expired"));
        }
        return ResponseEntity.status(200).body(new ResponseApi(foodlist.get(item_index).getName()+" is not Expired"));


    }

}
