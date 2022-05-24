package com.example.restaurantsysproj.Controllers;


import com.example.restaurantsysproj.models.FoodClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class FoodController {



    ArrayList<String > food = new ArrayList<>();


// get food
    @GetMapping("food")
    public ResponseEntity getFood(){

        return ResponseEntity.status(HttpStatus.OK).body(food);

    }

// adding food
    @PostMapping("food")
    public ResponseEntity<String> addFood(@RequestBody String typeOfFood){
        food.add(typeOfFood);
        return ResponseEntity.status(HttpStatus.CREATED).body(typeOfFood);

    }

    //alter food
    @PutMapping("food/{index}")
    public ResponseEntity<String> updateFood(String updFood , Integer index){
        if(index > food.size()-1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid index");
        }
        food.set(index , updFood);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updFood);


    }

    // deleting
    @DeleteMapping("food/{index}")
    public ResponseEntity<String> delFood(String rmFood , Integer index){
        food.remove(index);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(rmFood);

    }

    //Add Quantity for specific Food
    @PostMapping("food/{index}")
    public ResponseEntity addQtyToFood(FoodClass foodClass , Integer index){
        if(foodClass.getQuantity() < 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("adding qty is invalid (you cant add (-) qty)");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(foodClass.getQuantity());

    }
    // Remove Quantity from specific Food
    @DeleteMapping("food/{index}")
    public ResponseEntity delQtyFromFood(FoodClass foodClass, Integer index){
        food.remove(foodClass.getQuantity());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(foodClass.getQuantity());

    }




}
