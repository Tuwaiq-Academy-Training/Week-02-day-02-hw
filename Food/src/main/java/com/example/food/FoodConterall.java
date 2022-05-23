package com.example.food;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.util.Date;
@RestController
public class FoodConterall {
    private ArrayList<Food> foods = new ArrayList<>();

    @GetMapping("food")
    public ArrayList<Food> getfood() {
        return foods;
    }

    @PostMapping("food")
    public ResponseEntity<ResponsApi> addfood(@RequestBody Food food) {

        foods.add(food);
        return ResponseEntity.status(200).body(new ResponsApi("Food " + food.getName() + " Add Food"));
    }

    @PutMapping("food/{index}")
    public ResponseEntity<ResponsApi> editfood(@PathVariable int index, @RequestBody Food name) {
        if (index > foods.size() - 1) {
            return ResponseEntity.status(400).body(new ResponsApi("The index invalid"));
        }

        foods.set(index, name);
        return ResponseEntity.status(200).body(new ResponsApi("Food " + name.getName() + "update"));
    }


    @DeleteMapping("food/{index}")
    public ResponseEntity<ResponsApi> delFood(@PathVariable int index) {
        if (index > foods.size() - 1) {
            return ResponseEntity.status(400).body(new ResponsApi("The index invalid"));
        }
        foods.remove(index);
        return ResponseEntity.status(200).body(new ResponsApi("User" + index + "del user"));
    }

    @GetMapping("food/add/{id}/{amount}")
    public ResponseEntity<ResponsApi> addQty(@PathVariable int amount, @PathVariable String id) {
        for (Food f : foods) {
            if (f.getId().equals(id)) {
                f.setQuantity(f.getQuantity() + amount);
                return ResponseEntity.status(201).body(new ResponsApi(" Quantity successfully added"));
            }
        }
        return ResponseEntity.status(400).body(new ResponsApi("invalid food id"));

    }

    @GetMapping("food/remove/{id}/{amount}")
    public ResponseEntity<ResponsApi> removeQty(@PathVariable int amount, @PathVariable String id) {
        for (Food f : foods) {
            if (f.getId().equals(id)) {
                f.setQuantity(f.getQuantity() - amount);
                return ResponseEntity.status(201).body(new ResponsApi(" Quantity successfully remove"));
            }
        }
        return ResponseEntity.status(400).body(new ResponsApi("invalid food id"));

    }
    @GetMapping("food/{id}")
    public ResponseEntity<ResponsApi> isFoodExpired(@PathVariable String id) {
        for (Food f : foods) {
            if (f.getId().equals(id)) {
                boolean isExpired = f.getExpiry_dat().before(new Date());
                if(isExpired) {
                    return ResponseEntity.status(201).body(new ResponsApi(" food expired"));
                } else {
                    return ResponseEntity.status(201).body(new ResponsApi(" food not expired"));
                }
            }
        }
        return ResponseEntity.status(400).body(new ResponsApi("invalid food id"));
    }

}
