package com.example.hw22.RestaurantsSystem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ControllerFood {
    private ArrayList<Food> foods = new ArrayList<>();
    @GetMapping("food")
    public ResponseEntity<ArrayList<Food>> getFood(){
        return ResponseEntity.status(HttpStatus.OK).body(foods);
    }
    @PostMapping("food")
    public ResponseEntity<ApiFood> newFood(@RequestBody Food food){
        foods.add(food);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiFood("Add new: " +food.getName()+ " To The List",200));
    }
    @PutMapping("food/{index}")
    public ResponseEntity<ApiFood> updateFood(@PathVariable Integer index, @RequestBody Food food){
       try {
           foods.set(index,food);
           return ResponseEntity.status(HttpStatus.OK).body(new ApiFood("Edat value: "+food.getName()+" From The List!",200));
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.OK).body(new ApiFood("Sorry but Your request is very bad :(",400));
       }
    }
    @DeleteMapping("food/{index}")
    public ResponseEntity<ApiFood> removeFood(@PathVariable Integer index){
      try {
          System.out.println(index);
          foods.remove(index);
          return ResponseEntity.status(HttpStatus.OK).body(new ApiFood("The Item: Deleted!" ,200));
      } catch (Exception e)
      {
          return ResponseEntity.status(HttpStatus.OK).body(new ApiFood("Sorry but Your request is very bad :(",400));
      }
    }
    @PostMapping("food/{index}")
    public ResponseEntity<ApiFood> addQuantity(@PathVariable Integer index,@RequestBody Food request){
          try {
              request.getQuantity().toString();
              int qun = 0;
              for (int i = 0; i < foods.size(); i++) {
                  qun += foods.get(i).getQuantity();
              }
              foods.add(request);
              return ResponseEntity.status(HttpStatus.OK).body(new ApiFood("Value Adedd",200));
          } catch (Exception e){
              return ResponseEntity.status(HttpStatus.OK).body(new ApiFood("Sorry but Your request is very bad :(",400));
          }
    }
    @DeleteMapping("food/{index}/removeQuantity")
    public ResponseEntity<ApiFood> removeQuantity(@PathVariable Integer index,@RequestBody Food Quantity){
       if (Quantity.getQuantity().toString().length()>5){
           foods.remove(Quantity);
           return ResponseEntity.status(HttpStatus.OK).body(new ApiFood("Value Deleted!",200));
       } else {
           return ResponseEntity.status(HttpStatus.OK).body(new ApiFood("Sorry but Your request is very bad :(",400));
       }
    }
    @GetMapping("food/{index}/expired")
    public ResponseEntity<ArrayList<Food>> getFoodExpired(@PathVariable Integer index,@RequestBody Food expired){
       try {
           return ResponseEntity.status(HttpStatus.OK).body(foods);
       } catch (Exception e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(foods);
       }
    }
}
