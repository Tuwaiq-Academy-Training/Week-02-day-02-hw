package com.example.assignment6.controllers;

import com.example.assignment6.model.Food;
import com.example.assignment6.model.ResponseAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class foodController {

    private ArrayList<Food> foods = new ArrayList<>();

    @GetMapping("food")
    public ResponseEntity<ArrayList<Food>> getFoods() {
        return ResponseEntity.status(200).body(foods);
    }

    @PostMapping("food")
    public ResponseEntity<ResponseAPI> addFoods(@RequestBody Food food){
        foods.add(food);
        return ResponseEntity.status(200).body(new ResponseAPI("Food " + food.getName() + " added"));
    }

    @PutMapping("food/{index}")
    public ResponseEntity<ResponseAPI> editFoods(@RequestBody Food food, @PathVariable Integer index){
        if(index > foods.size()-1){
            return ResponseEntity.status(400).body(new ResponseAPI("The index " + index
                    + " is more than array length " + foods.size()));
        }
        foods.set(index,food);
        return ResponseEntity.status(200).body(new ResponseAPI("Food: " + food.getName() + " updated"));
    }

    @DeleteMapping("food/{index}")
    public ResponseEntity<ResponseAPI> deleteFoods(@PathVariable Integer index){
        if(index > foods.size()-1){
            return ResponseEntity.status(400).body(new ResponseAPI("The index " + index
                    + " is more than array length " + foods.size()));
        }
        foods.remove(index);
        return ResponseEntity.status(200).body(new ResponseAPI("Food deleted"));
    }

    @PutMapping("food/{id}")
    public ResponseEntity<ResponseAPI> addQuantity(@PathVariable String id){
        for (int i = 0; i < foods.size(); i++) {
            if(foods.get(i).getId().equals(id)) {
                foods.get(i).setQuantity(50);
                return ResponseEntity.status(200).body(new ResponseAPI("Your food quantity is " + foods.get(i).getQuantity()));
            }
        }
        return ResponseEntity.status(400).body(new ResponseAPI("The id is not exist "));
    }

    @DeleteMapping("food/delete/{id}")
    public ResponseEntity<ResponseAPI> deleteFoods(@PathVariable String id){
        for (int i = 0; i < foods.size(); i++) {
            if(foods.get(i).getId().equals(id)) {
                foods.get(i).setQuantity(null);
                return ResponseEntity.status(200).body(new ResponseAPI("Your food quantity is " + foods.get(i).getQuantity()));
            }
        }
        return ResponseEntity.status(400).body(new ResponseAPI("The id is not exist "));
    }

}
