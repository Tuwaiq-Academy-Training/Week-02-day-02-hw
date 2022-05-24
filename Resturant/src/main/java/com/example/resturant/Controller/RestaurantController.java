package com.example.resturant.Controller;

import com.example.resturant.Model.Food;
import com.example.resturant.Model.ResponseAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
public class RestaurantController {
    private ArrayList<Food> foods = new ArrayList<>();

    @GetMapping("food")
    public ResponseEntity<Object> getAllFood() {
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

    @PutMapping("food/{id}")
    public ResponseEntity<ResponseAPI> editFood(@PathVariable Integer id, @RequestBody Food food) {
//        if (index > foods.size() - 1 || index < 0) {
//            return ResponseEntity.status(400).body(new ResponseAPI("index is invalid!"));
//        }
        Food food1 = null;
        int index = 0;
        for (Food food2 : foods) {
            int id2 = Integer.parseInt(food2.getId());
            if (id2 == id) {
                food1 = food;
                break;
            }
            index++;
        }
        if (food1 == null) {
            return ResponseEntity.status(400).body(new ResponseAPI("Wrong ID Provided!"));
        }
        foods.set(index, food);
        return ResponseEntity.status(200).body(new ResponseAPI("Food Has been Updated!"));
    }

    @DeleteMapping("food/{id}")
    public ResponseEntity<ResponseAPI> deleteFood(@PathVariable Integer id) {
        Food food2 = null;
        int index = 0;
        for (Food food : foods) {
            int id2 = Integer.parseInt(food.getId());
            if (id2 == id) {
                food2 = food;
                break;
            }
            index++;
        }
        if (food2 == null) {
            return ResponseEntity.status(400).body(new ResponseAPI("Wrong ID Provided!"));
        }
        foods.remove(foods.get(index));
        return ResponseEntity.status(200).body(new ResponseAPI("Food Has been Deleted!"));
    }

    @PutMapping("food/{id}/{qty}")
    public ResponseEntity<ResponseAPI> addQTY(@PathVariable Integer id, @PathVariable Integer qty) {
        Food food2 = null;
        int index = 0;
        for (Food food : foods) {
            int id2 = Integer.parseInt(food.getId());
            if (id2 == id) {
                food2 = food;
                break;
            }
            index++;
        }
        if (food2 == null) {
            return ResponseEntity.status(400).body(new ResponseAPI("Wrong ID Provided!"));
        }

        if (qty > 0) {
            food2.setQty(food2.getQty() + qty);
            foods.set(index, food2);
            return ResponseEntity.status(200).body(new ResponseAPI("Food Quantity Has been Updated!"));
        } else {
            return ResponseEntity.status(400).body(new ResponseAPI("qty is invalid!"));
        }
    }

    @PutMapping("food/{id}/remove/{qty}")
    public ResponseEntity<ResponseAPI> removeQTY(@PathVariable Integer id, @PathVariable Integer qty) {
        Food food2 = null;
        int index = 0;
        for (Food food : foods) {
            int id2 = Integer.parseInt(food.getId());
            if (id2 == id) {
                food2 = food;
                break;
            }
            index++;
        }
        if (food2 == null) {
            return ResponseEntity.status(400).body(new ResponseAPI("Wrong ID Provided!"));
        }


        if (qty > 0) {
            food2.setQty(food2.getQty() - qty);
            foods.set(index, food2);
            return ResponseEntity.status(200).body(new ResponseAPI("Food Quantity Has been Updated!"));
        } else {
            return ResponseEntity.status(400).body(new ResponseAPI("qty is invalid!"));
        }
    }

    @GetMapping("food/{id}/check")
    public ResponseEntity<ResponseAPI> checkExpiry(@PathVariable Integer id) {

        LocalDate localDate = LocalDate.now();
        Food food2 = null;
        for (Food food : foods) {
            int id2 = Integer.parseInt(food.getId());
            if (id2 == id) {
                food2 = food;
                break;
            }
        }
        if (food2 == null) {
            return ResponseEntity.status(400).body(new ResponseAPI("Wrong ID Provided!"));
        }

        int result = food2.getExpiryDate().compareTo(localDate);
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


    //helper function
//    public Food checkID() {}
//
//    }
