package controller;


import com.example.springhw22.Food;
import com.example.springhw22.Quantity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class FoodControll {
    public ArrayList<Food> food = new ArrayList();
    @GetMapping("food")
    public ResponseEntity getFood(){
        return ResponseEntity.status(200).body(food);
    }

    @PostMapping("food")
    public ResponseEntity addFood(@RequestBody Food f){
        food.add(f);
        return ResponseEntity.status(201).body("Food added");
    }
    @PutMapping("update/{index}")
    public ResponseEntity updateFood(@PathVariable Integer index,@RequestBody Food f){
        if(index >  food.size()-1){
            return ResponseEntity.status(400).body("Food not found");
        }
        food.set((int) index,f);
        return ResponseEntity.status(200).body("Food update");
    }

    @DeleteMapping("delete/{index}")
    public ResponseEntity deleteFood(@PathVariable Integer index){
        if(index > food.size()-1){
            return ResponseEntity.status(400).body("food id  not found");
        }
        food.remove((int)index);
        return ResponseEntity.status(201).body("food deleted");
    }

    @PutMapping("quantity") //+ quantity
    public ResponseEntity addQuantity(@RequestBody Quantity q){
        if(q.getID() > food.size()-1){
            return ResponseEntity.status(400).body("Food not found");
        }
        food.get(q.getID()).setQuantity(food.get(q.getID()).getQuantity() + q.getQuantity());
        return ResponseEntity.status(201).body("Quantity updated");
    }

    @PutMapping("remove")
    public ResponseEntity removeQuantity(@RequestBody Quantity q){
        if(q.getID() > food.size()-1){
            return ResponseEntity.status(400).body("Food not found");
        }
        food.get(q.getID()).setQuantity(food.get(q.getID()).getQuantity() - q.getQuantity());
        return ResponseEntity.status(201).body("removed");
    }


}
