package com.example.atheerhw2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.time.LocalDate;


@RestController
public class Restaurants {

    private ArrayList<Food> foods = new ArrayList<>();


    @GetMapping("food")
    public ResponseEntity<ArrayList<Food>> getFood() {
        return ResponseEntity.status(200).body(foods);
    }

    @PostMapping("food")
    public ResponseEntity<ResponseAPI> addFood(@RequestBody Food food) {
        foods.add(food);
        return ResponseEntity.status(200).body(new ResponseAPI("New Food " + food.getName() + " added"));
    }

    @PutMapping("food/update/{index}")
    public ResponseEntity<ResponseAPI> updateFood(@RequestBody Food food, @PathVariable Integer index) {
        if (index > foods.size() - 1) {
            return ResponseEntity.status(400).body(new ResponseAPI(
                    "The index " + index + " is more than array length " + foods.size()));
        }
        foods.set(index, food);
        return ResponseEntity.status(200).body(
                new ResponseAPI("Food " + food.getName() + " updated"));
    }

    @DeleteMapping("food/del/{index}")
    public ResponseEntity<ResponseAPI> deleteFood(@PathVariable Integer index) {
        if (index > foods.size() - 1) {
            return ResponseEntity.status(400).body(new ResponseAPI("The index " + index
                    + " is more than array length " + foods.size()));
        }
        foods.remove(index);
        return ResponseEntity.status(200).body(new ResponseAPI("Food deleted"));
    }

    @DeleteMapping("food/delQuantity/{index}")
    public ResponseEntity<ResponseAPI> deleteQuantity(@PathVariable Integer index) {

        if (index > foods.size() - 1) {
            return ResponseEntity.status(400).body(new ResponseAPI("The index " + index
                    + " is more than array length " + foods.size()));
        }

        for (int i = 0; i < foods.size(); i++) {
            if (foods.get(i).getID() == index)

                foods.get(i).setQuantity(0);

        }


        return ResponseEntity.status(200).body(new ResponseAPI("Quantity deleted"));
    }

    @PutMapping("food/addQuantity/{Quantity}/{index}")
    public ResponseEntity<ResponseAPI> addQuantity(@PathVariable Integer  Quantity,@PathVariable Integer index) {

        if (index > foods.size() - 1) {
            return ResponseEntity.status(400).body(new ResponseAPI("The index " + index
                    + " is more than array length " + foods.size()));
        }

        for (int i = 0; i < foods.size(); i++) {
            if (foods.get(i).getID() == index)

                foods.get(i).setQuantity(Quantity);

        }


        return ResponseEntity.status(200).body(new ResponseAPI("Quantity added"));
    }

    @GetMapping("food/expired")
    public ResponseEntity isExpired (@RequestBody Food food) {
        LocalDate currentDate = LocalDate.now();
        LocalDate currentDateMinus6Months = currentDate.minusMonths(6);

        LocalDate date1 = food.getExpiryDate();
        if (date1.isBefore(currentDateMinus6Months)) {
            return ResponseEntity.status(200).body(new ResponseAPI("Not expired !"));

        }
        return ResponseEntity.status(200).body(new ResponseAPI("Not expired !"));
        }







}