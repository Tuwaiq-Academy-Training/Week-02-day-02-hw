package com.example.springrestaurant.controllers;


import com.example.week2day2.model.Food;
import com.example.week2day2.model.ResponseApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
public class restaurantSystem {

    private ArrayList<Food> foodes =new ArrayList();

    @GetMapping("food")
    public ResponseEntity getFood(){
    return ResponseEntity.status(200).body(foodlist);
 }

 
 @GetMapping("food/{index}/expiry")
 public ResponseEntity checkExpiry(@PathVariable Integer id){
     boolean Found = false;
     boolean Expired = false;
     int index = 0;
     for(int i = 0;i < foodes.size(); i++){
        if(foodes.get(i).getId() == id){
            Found =t rue;
             if(LocalDate.now().isAfter(foodes.get(i).getExpirydate())){
                Expired = true;
            }
            index = i;
        }
     }
     if(Found == false){
         return ResponseEntity.status(400).body(new RestaurantApi("not found"));
     }

     if(Expired == 0){
         return ResponseEntity.status(200).body(new RestaurantApi(foodes.get(index).getName()+" is Expired"));
     }
     return ResponseEntity.status(200).body(new RestaurantApi(foodes.get(index).getName()+" is not Expired"));
 }


   @PostMapping("food")
    public ResponseEntity addFood(@RequestBody Food food){
        foodes.add(food);
       return ResponseEntity.status(201).body(new RestaurantApi("Food added successfully"));
   }


 @PutMapping("food/{index}/addqty")
 public ResponseEntity addQuantity(@PathVariable Integer id, @RequestBody Integer moreQty){

     boolean Found = false;
     for(int i = 0;i < foodes.size(); i++){
         if(foodes.get(i).getId() == id){
             Found = true;
             foodes.get(i).setQuantity(foodes.get(i).getQuantity()+moreQty);

         }
     }
     if(Found == false){
         return ResponseEntity.status(400).body(new RestaurantApi("not found"));
     }

     return ResponseEntity.status(201).body(new RestaurantApi("added"));
 }


 @PutMapping("food/{index}/rmqty")
 public ResponseEntity removeQuantity(@PathVariable Integer id, @RequestBody Integer Qty){

     boolean Found = false;
     for(int i = 0;i < foodes.size(); i++){
         if(foodes.get(i).getId() == id){
             isFound=true;
             if(foodes.get(i).getQuantity() - Qty <0 ){
                 return ResponseEntity.status(400).body(new RestaurantApi("Removing"));
             }
             foodes.get(i).setQuantity(foodes.get(i).getQuantity()-Qty);

         }
     }
     if(Found == false){
         return ResponseEntity.status(400).body(new RestaurantApi("not found"));
     }

     return ResponseEntity.status(201).body(new RestaurantApi("successfully removed "));
 }



   @PutMapping("food/{index}")
    public ResponseEntity updateFood(@PathVariable Integer index, @RequestBody Food food){
       if(index > foodes.size()-1 || index < 0){
           return ResponseEntity.status(400).body(new RestaurantApi("you have entered an invalid index :"+index));
       }
       foodes.set(index,food);
       return ResponseEntity.status(201).body(new RestaurantApi("Food has been successfully updated "));
   }


  @DeleteMapping("food/{index}")
  public ResponseEntity deleteFood(@PathVariable Integer index){
      if(index > foodes.size()-1 || index < 0){
          return ResponseEntity.status(400).body(new RestaurantApi("you have entered an invalid index :"+index));
      }
      foodes.remove((int) index);
      return ResponseEntity.status(200).body(new RestaurantApi("Food has been successfully deleted"));
  }
}