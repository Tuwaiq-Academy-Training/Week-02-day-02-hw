package com.example.springday02homework.controller;


import com.example.springday02homework.model.Food;
import com.example.springday02homework.model.Food;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
public class FoodController {
    
    ArrayList<Food> foods=new ArrayList<Food>();

    @GetMapping("food")
    public ResponseEntity getFood(){
        return ResponseEntity.status(200).body(foods);
    }

    @PostMapping("food")
    public ResponseEntity postFood(@RequestBody Food food){
        foods.add(food);
        return ResponseEntity.status(201).body(foods);
    }

    @PutMapping("food/{id}")
    public ResponseEntity updateFood(@RequestBody Food food , @PathVariable String id ){
        Integer index=getIndex(id);
        if(index==0){
            return ResponseEntity.status(400).body(new ResponseAPI("there is no food"));
        }
        foods.set(index,food);
        return ResponseEntity.status(200).body(new ResponseAPI(food.getName()+" has updated"));
    }

    @DeleteMapping("food/{id}")
    public ResponseEntity deleteFood(@PathVariable String id){
        Integer index=getIndex(id);
        if(index==null){
            return ResponseEntity.status(400).body(new ResponseAPI("there is no food"));
        }
        foods.remove((int)index);
        return ResponseEntity.status(200).body(new ResponseAPI("food has deleted"));
    }

    @PutMapping("food/quantity/{id}")
    public ResponseEntity updateQuantity(@RequestBody int quantity,@PathVariable String id ){
        Integer index=getIndex(id);
        if(index==null){
            return ResponseEntity.status(400).body(new ResponseAPI("Bad request"));
        }
        foods.get(index).setQuantity(quantity);
        return ResponseEntity.status(200).body(new ResponseAPI("food has updated"));
    }

    @DeleteMapping("food/quantity/{id}")
    public ResponseEntity removeQuantity(@PathVariable String id ){
        Integer index=getIndex(id);
        if(index==null){
            return ResponseEntity.status(400).body(new ResponseAPI("Bad request"));
        }
        foods.get(index).setQuantity(0);
        return ResponseEntity.status(200).body(new ResponseAPI("Quantity has removed"));
    }

    @GetMapping("food/checkexpire/{id}")
    public ResponseEntity checkExpireDate(@PathVariable String id){
        Integer index=getIndex(id);
        if(index==null){
            return ResponseEntity.status(400).body(new ResponseAPI("Bad request"));
        }
        if (foods.get(index).getExpiryDate().isBefore(LocalDate.now())){
            return ResponseEntity.status(200).body(new ResponseAPI("expired food"));
        }
        return ResponseEntity.status(200).body(new ResponseAPI("food not expired"));
    }



    public Integer getIndex(String id){
        for (int i = 0; i < this.foods.size(); i++) {
            if (foods.get(i).getId().equals(id)){
                return i;
            }
        }
        return null;
    }

    
}
