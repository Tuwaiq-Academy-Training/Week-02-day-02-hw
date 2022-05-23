package com.example.h02w02.MainControllers;

import com.example.h02w02.models.Food;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class FoodContoller {
    List<Food> foods = new ArrayList<>();

    @GetMapping("food")
    public ResponseEntity getFood(){
        return ResponseEntity.status(200).body(foods);
    }

    @PostMapping("food")
    public ResponseEntity addFood(@RequestBody Food f){
        foods.add(f);
        return ResponseEntity.status(201).body(f.getName() + " was successfully added");
    }

    @PutMapping("food/{index}")
    public ResponseEntity updateFood(@RequestBody Food f, @PathVariable int index){
        if(index > foods.size()){
            return ResponseEntity.status(400).body("Invalid index.");
        }else
            foods.set(index,f);
            return ResponseEntity.status(201).body(f.getName() + " was successfully updated");
    }

    @DeleteMapping("food/{index}")
    public ResponseEntity deleteFood(@PathVariable int index){
        if(index > foods.size()){
            return ResponseEntity.status(400).body("Invalid index.");
        }else {
            Food foo = foods.remove(index);
            return ResponseEntity.status(200).body(foo + " was successfully removed");
        }
    }

    @PutMapping("food/add/{id}")
    public ResponseEntity addQty(@RequestParam int amount, int id){
        for(Food f: foods){
            if(f.getId().equals(id)){
                f.setQty(f.getQty() + amount);
                return ResponseEntity.status(201).body(" Quantity successfully added");
            }
        }
        return ResponseEntity.status(400).body(" Invalid ID");
    }
    @PutMapping("food/remove/{id}")
    public ResponseEntity removeQty(@RequestParam int amount, int id){
        for(Food f: foods){
            if(f.getId().equals(id)){
                f.setQty(f.getQty()-amount);
                return ResponseEntity.status(201).body(" Quantity successfully removed");
            }
        }
        return ResponseEntity.status(400).body(" Invalid ID");
    }
    @GetMapping("food/expiry/{id}")
    public ResponseEntity checkExpiryDate(@PathVariable int id){

        Date todayDate = new Date();

        for(Food f : foods){
            if(f.getId().equals(id)){
                if(f.getDate().after(todayDate)){
                    return ResponseEntity.status(200).body("It is expired");
                }
                else{return ResponseEntity.status(200).body("It is not expired");}
            }
        }
        return ResponseEntity.status(400).body(" Invalid ID");
    }

}
