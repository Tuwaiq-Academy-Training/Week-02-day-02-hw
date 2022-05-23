package com.example.Work.Restaurants;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


@RestController
public class FoodController {
    public ArrayList<Food> food = new ArrayList<>();
    @GetMapping("food")
    public ResponseEntity getFood(){
        return ResponseEntity.status(200).body(food);
    }
    @PostMapping("food")
    public ResponseEntity addFood(@RequestBody Food f1){
        food.add(f1);
        return ResponseEntity.status(201).body("Food added");
    }
    @PutMapping("food/{index}")
    public ResponseEntity updateFood(@PathVariable Integer index,@RequestBody Food f1){
        if(index >  food.size()-1){
            return ResponseEntity.status(400).body("Item not found");
        }
        food.set((int) index,f1);
        return ResponseEntity.status(200).body("Food added");
    }
    @DeleteMapping("food/{index}")
    public ResponseEntity deleteFood(@PathVariable Integer index){
        if(index > food.size()-1){
            return ResponseEntity.status(400).body("Food not found!");
        }
        food.remove((int)index);
        return ResponseEntity.status(201).body("Food deleted!");
    }
    @PutMapping("Addquantity")
    public ResponseEntity addQuant(@RequestBody Quantity q1){
        if(q1.getID() > food.size()-1){
            return ResponseEntity.status(400).body("Not found!");
        }
        food.get(q1.getID()).setQuantity(food.get(q1.getID()).getQuantity() + q1.getQuantity());
        return ResponseEntity.status(201).body("Quantity updated!");
    }
    @PutMapping("Deletequantity")
    public ResponseEntity removeQuant(@RequestBody Quantity q1){
        if(q1.getID() > food.size()-1){
            return ResponseEntity.status(400).body("Not found!");
        }
        food.get(q1.getID()).setQuantity(food.get(q1.getID()).getQuantity() - q1.getQuantity());
        return ResponseEntity.status(201).body("Quantity removed!");
    }
    @GetMapping("expired/{index}")
    public ResponseEntity checkDate(@PathVariable Integer index){
        if(index > food.size()-1){
            return ResponseEntity.status(401).body("Not found!");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date expirydate = null;
        try{
            expirydate = formatter.parse(food.get((int)index).getExpiryDate());
        }catch (ParseException e){
            return ResponseEntity.status(400).body("wrong format use -> dd/mm/yyyy");
        }
        Date currentDate = new Date();
        if(expirydate.after(currentDate)){
            return ResponseEntity.status(201).body("Not expired!");
        }
        return ResponseEntity.status(201).body("Item expired!");

    }
}