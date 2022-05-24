package com.example.HomeWork22;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@RestController
public class RestaurantsSystem {

    private ArrayList<Food> foods = new ArrayList<>();

    @GetMapping("food")
    public ResponseEntity getFood(){
        return ResponseEntity.status(200).body(foods);
    }

    @PostMapping("food")
    public ResponseEntity addFood(@RequestBody Food food){
        foods.add(food);
        return ResponseEntity.status(201).body(food);
    }

    @PutMapping("food/{index}")
    public ResponseEntity updateFood(@PathVariable int index, @RequestBody Food food){
        if( index > foods.size()-1  ){
            return ResponseEntity.status(400).body("Food invalid");
        }else
            foods.set(index,food);
        return ResponseEntity.status(200).body( " Food " + foods.get(index).getName() + " is Update");
    }

    @DeleteMapping("food/{id}")
    public ResponseEntity deleteFood ( @PathVariable int id){
        if ( id > foods.size() - 1 ) {
            return ResponseEntity.status(200).body("there are no user");
        }
        foods.remove(id);
        return ResponseEntity.status(200).body(" Food " + foods.get(id).getName()+ "deleted");
    }

    @PutMapping("food/{id}/add")
    public ResponseEntity addFoodQuantity(@PathVariable int id, @RequestParam(name="qty") int quantity){
        if (id > foods.size() - 1 ) {
            return ResponseEntity.status(200).body("there are no food with index " + id );
        }
        foods.get(id).setQuantity( foods.get(id).getQuantity() + quantity);
        return ResponseEntity.status(201).body(foods.get(id));
    }
    @PutMapping("food/{id}/remove")
    public ResponseEntity addRemoveQuantity(@PathVariable int id, @RequestParam(name="qty") int quantity){
        if ( id > foods.size() - 1 ) {
            return ResponseEntity.status(200).body("there are no food with index " + id );
        }
        foods.get(id).setQuantity( foods.get(id).getQuantity() - quantity);
        return ResponseEntity.status(201).body(foods.get(id));
    }

    @GetMapping("food/{id}/expired")
    public ResponseEntity checkExpired(@PathVariable int id){
        if ( id > foods.size() - 1 ) {
            return ResponseEntity.status(200).body("there are no food with index " + id );
        }
        boolean expired = foods.get(id).getDate().after(new Date());
        return ResponseEntity.status(201).body(expired ? "food is expired " : "food not expired yet");
    }



}
