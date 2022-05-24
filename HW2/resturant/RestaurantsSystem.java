package com.demo.HW2.resturant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("/food")
public class RestaurantsSystem {
    ArrayList<Food> foods = new ArrayList<Food>();

    @GetMapping()
    public ResponseEntity getFood(){
        return ResponseEntity.status(200).body(foods);
    }

    @PostMapping()
    public ResponseEntity addFood(@RequestBody Food food){
        foods.add(food);
        return ResponseEntity.status(201).body(foods);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateFood(@RequestBody Food food , @PathVariable int id ){
        Integer index = getById(id);
        if(index == 0){
            return ResponseEntity.status(400).body(
                    new ResponseAPI("there is no food"));
        }
        foods.set(index ,food);
        return ResponseEntity.status(200).body(
                new ResponseAPI(food.getName()+ " has updated"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFood(@PathVariable int id){
        Integer index = getById(id);
        if(index == null){
            return ResponseEntity.status(400).body(new ResponseAPI("bad request, there is no food"));
        }
        foods.remove((int)index);
        return ResponseEntity.status(200).body(
                new ResponseAPI("bad request, food has deleted"));
    }

    @PutMapping("/quantity/{id}")
    public ResponseEntity updateQuantity(@RequestBody int quantity,@PathVariable int id ){
        Integer index = getById(id);
        if(index==null){
            return ResponseEntity.status(400).body
                    (new ResponseAPI("Bad request"));
        }
        foods.get(index).setQuantity(quantity);
        return ResponseEntity.status(200).body(
                new ResponseAPI("food has updated"));
    }

    @PutMapping("/checkExpire/{id}")
    public ResponseEntity checkExpireDate(@PathVariable int id){
        Integer index = getById(id);
        if(index==null){
            return ResponseEntity.status(400).body(
                    new ResponseAPI("Bad request"));
        }
        if (foods.get(index).getExpiryDate().isBefore(LocalDate.now())){
            return ResponseEntity.status(200).body(
                    new ResponseAPI("expired food"));
        }
        return ResponseEntity.status(200).body(
                new ResponseAPI("food unexpired"));
    }

    @DeleteMapping("/quantity/{id}")
    public ResponseEntity removeQuantity(@PathVariable int id ){
        Integer index = getById(id);
        if(index == null){
            return ResponseEntity.status(400).body(new ResponseAPI(
                    "Bad request"));
        }

        foods.get(index).setQuantity(0);
        return ResponseEntity.status(200).body(new ResponseAPI(
                "Quantity has removed"));
    }

    public Integer getById(int id){
        for (int i = 0; i < this.foods.size(); i++) {
            if (foods.get(i).getId() == id)){
                return i;
            }
        }
        return null;
    }
}