package com.example.homework62.Controller;

import com.example.homework62.Model.Food;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
public class FoodController {
    private ArrayList<Food> foods = new ArrayList<>();

    @GetMapping("food")
    public ResponseEntity getFood(){
        if (foods.size() < 0){
            return ResponseEntity.status(400).body("Array is empty");
        }else {
            return ResponseEntity.status(201).body(foods);
        }
    }

    @PostMapping("food")
    public ResponseEntity addFood(@RequestBody Food food){
        foods.add(food);
        return ResponseEntity.status(201).body("Add food to array");
    }

    @PutMapping("food/{index}")
    public ResponseEntity updateFood(@PathVariable Integer index, @RequestBody Food food){
        if (index > foods.size()-1){
            return ResponseEntity.status(401).body("index out of range "+foods.size());
        }else {
            foods.set((int)index,food);
            return ResponseEntity.status(201).body("successful update");

        }
    }
    @DeleteMapping("food/{index}")
    public ResponseEntity deleteFood(@PathVariable Integer index){

        if (index > foods.size()-1){
            return ResponseEntity.status(401).body("index out of range "+foods.size());
        }else {
            foods.remove((int)index);
            return ResponseEntity.status(201).body("successful deleted");
        }
    }

    @PutMapping("food/{index}/{qty}")
    public ResponseEntity addQty(@PathVariable Integer index, @PathVariable Integer qty){
        if (index > foods.size()-1){
            return ResponseEntity.status(401).body("index out of range "+foods.size());
        }
        if (qty > 0){
            Food qtyNew = foods.get(index);
            qtyNew.setQty(qtyNew.getQty() + qty);
            foods.set(index,qtyNew);
            return ResponseEntity.status(201).body("Quantity updated");
        }else {
            return ResponseEntity.status(401).body("invalid qty");
        }
    }

    @PutMapping("food/delete/{index}/{qty}")
    public ResponseEntity deleteQty(@PathVariable Integer index,@PathVariable Integer qty){
        if(index > foods.size()-1){
            return ResponseEntity.status(401).body("invalid index");
        }
        Food qtyNew = foods.get(index);
        if (qty < 0){
            return ResponseEntity.status(401).body("invalid qty");
        }else {
            qtyNew.setQty(qtyNew.getQty()-qty);
            foods.set(index,qtyNew);
            return ResponseEntity.status(201).body("Quantity updated");
        }
    }

    @GetMapping("food/check/{index}")
    public ResponseEntity checkFood(@PathVariable Integer index){
        if (index > foods.size()-1){
            return ResponseEntity.status(401).body("index out of range "+foods.size());
        }
        LocalDate localDate = LocalDate.now();
        int check = foods.get(index).getExpiryDate().compareTo(localDate);
        if (check <= 0){
            return ResponseEntity.status(201).body("Expired date");
        }else {
            return ResponseEntity.status(201).body("Not expired date");
        }
    }
}
