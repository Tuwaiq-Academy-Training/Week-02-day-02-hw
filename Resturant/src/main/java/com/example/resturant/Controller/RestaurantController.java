package com.example.resturant.Controller;

import com.example.resturant.Model.Food;
import com.example.resturant.Model.ResponseAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
public class RestaurantController {
    private ArrayList<Food> foods= new ArrayList<>();

    @GetMapping("food")
    public ResponseEntity<Object> getAllFood(){
        if (foods.size() > 0) {
            return ResponseEntity.status(200).body(foods);
        } else {
            return ResponseEntity.status(400).body(new ResponseAPI("No Food Has been Added yet!"));
        }
    }

    @PostMapping("food")
    public ResponseEntity<ResponseAPI> addFood(@RequestBody Food food) {
        this.foods.add(food);
        return ResponseEntity.status(200).body(new ResponseAPI("Food Has been Added!"));
    }

    @PutMapping("food/{index}")
    public ResponseEntity<ResponseAPI> editFood(@PathVariable Integer index,@RequestBody Food food) {
        if(index > foods.size() -1 || index < 0) {
            return ResponseEntity.status(400).body(new ResponseAPI("index is invalid!"));
        }
        foods.set(index,food);
        return ResponseEntity.status(200).body(new ResponseAPI("Food Has been Updated!"));
    }

    @DeleteMapping("food/{index}")
    public ResponseEntity<ResponseAPI> deleteFood(@PathVariable Integer index) {
        if(index > foods.size() -1 || index < 0) {
            return ResponseEntity.status(400).body(new ResponseAPI("index is invalid!"));
        }
        foods.remove(foods.get(index));
        return ResponseEntity.status(200).body(new ResponseAPI("Food Has been Deleted!"));
    }

    @PutMapping("food/{index}/{qty}")
    public ResponseEntity<ResponseAPI> addQTY(@PathVariable Integer index, @PathVariable Integer qty) {
        if(index > foods.size() -1 || index < 0) {
            return ResponseEntity.status(400).body(new ResponseAPI("index is invalid!"));
        }
        if(qty > 0) {
            Food newQTY = foods.get(index);
            newQTY.setQty(newQTY.getQty() + qty);
            foods.set(index, newQTY);
            return ResponseEntity.status(200).body(new ResponseAPI("Food Quantity Has been Updated!"));
        } else {
            return ResponseEntity.status(400).body(new ResponseAPI("qty is invalid!"));
        }
    }

    @PutMapping("food/{index}/remove/{qty}")
    public ResponseEntity<ResponseAPI> removeQTY(@PathVariable Integer index, @PathVariable Integer qty) {
        if(index > foods.size() -1 || index < 0) {
            return ResponseEntity.status(400).body(new ResponseAPI("index is invalid!"));
        }
        Food newQTY = foods.get(index);
        if(qty > 0) {
            newQTY.setQty(newQTY.getQty() - qty);
            foods.set(index,newQTY);
            return ResponseEntity.status(200).body(new ResponseAPI("Food Quantity Has been Updated!"));
        }else {
            return ResponseEntity.status(400).body(new ResponseAPI("qty is invalid!"));
        }
    }

    @GetMapping("food/{index}/check")
    public ResponseEntity<ResponseAPI> checkExpiry(@PathVariable Integer index) {
        if(index > foods.size() -1 || index < 0) {
            return ResponseEntity.status(400).body(new ResponseAPI("index is invalid!"));
        }
        LocalDate localDate = LocalDate.now();
        int result = foods.get(index).getExpiryDate().compareTo(localDate);
        if (result == 0) {
//            System.out.println("Date1 is equal to Date2");
            return ResponseEntity.status(200).body(new ResponseAPI("Food is expired!"));
        } else if (result > 0) {
//            System.out.println("Date1 is after Date2");
            return ResponseEntity.status(200).body(new ResponseAPI("Food is still not expired!"));
        } else if (result < 0) {
//            System.out.println("Date1 is before Date2");
            return ResponseEntity.status(200).body(new ResponseAPI("Food is expired!"));
        }
        return ResponseEntity.status(200).body(new ResponseAPI("something wrong!"));
    }
    }
