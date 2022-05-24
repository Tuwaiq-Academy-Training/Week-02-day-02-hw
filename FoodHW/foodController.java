package com.example.javaDay02.controllers;

import com.example.javaDay02.model.Food;
import com.example.javaDay02.model.Quantity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("food/")
public class foodController {
    public ArrayList<Food> food = new ArrayList<>();
    @GetMapping("")
    public ResponseEntity getFood(){
        return ResponseEntity.status(200).body(food);
    }
    @PostMapping("add")
    public ResponseEntity addFood(@RequestBody Food f1){
        food.add(f1);
        return ResponseEntity.status(201).body("Food added");
    }
    @PutMapping("update/{index}")
    public ResponseEntity updateFood(@PathVariable Integer index,@RequestBody Food f1){
        if(index >  food.size()-1){
            return ResponseEntity.status(400).body("Item not found");
        }
        food.set((int) index,f1);
        return ResponseEntity.status(200).body("Food added");
    }
    @DeleteMapping("delete/{index}")
    public ResponseEntity deleteFood(@PathVariable Integer index){
        if(index > food.size()-1){
            return ResponseEntity.status(400).body("User not found");
        }
        food.remove((int)index);
        return ResponseEntity.status(201).body("User deleted");
    }
    @PutMapping("addquantity")
    public ResponseEntity addQuant(@RequestBody Quantity q1){
        for( Food f1 : food){
            if(f1.getID().equals(q1.getID())){
                f1.setQuantity(f1.getQuantity() + q1.getQuant());
                return ResponseEntity.status(201).body("Quantity updated");
            }
        }
        return ResponseEntity.status(400).body("Couldn't find item");
    }
    @PutMapping("removequantity")
    public ResponseEntity removeQuant(@RequestBody Quantity q1){

        for( Food f1 : food){
            if(f1.getID().equals(q1.getID())){

                f1.setQuantity(f1.getQuantity() - q1.getQuant());


                return ResponseEntity.status(201).body("Quantity updated");
            }
        }
        return ResponseEntity.status(400).body("Couldn't find item");
    }
    @GetMapping("expired/{index}")
    public ResponseEntity checkDate(@PathVariable String index) {
        for (Food f1 : food) {
            if (f1.getID().equals(index.toString())) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date expirydate = null;
                try {
                    int indx = Integer.parseInt(index);
                    expirydate = formatter.parse(f1.getExpiryDate());
                } catch (ParseException e) {
                    return ResponseEntity.status(400).body("Date format is wrong, try dd/mm/yyyy");
                }
                Date currentDate = new Date();
                if (expirydate.after(currentDate)) {
                    return ResponseEntity.status(201).body("Not expired");
                } else {
                    return ResponseEntity.status(201).body("Item expired");
                }
            }


        }


        return ResponseEntity.status(400).body("Item not found");

    }
}
