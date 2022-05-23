package com.example.firstspringboot.comntroller;

import com.example.firstspringboot.Customer;
import com.example.firstspringboot.Restaurant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantsController {
    List<Restaurant> restaurants = new ArrayList<>();

    RestaurantsController() throws ParseException {


        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM");
        Date date1 = dt.parse("2023-01");
        Date date2 = dt.parse("2021-01");
        Date date3 = dt.parse("2022-09");
        Date date4 = dt.parse("2022-05");
        System.out.println(date1);
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM");
        System.out.println(dt1.format(date1));

        restaurants.addAll(
                List.of(
                        new Restaurant(0,"Fish", 55, date1, 8),
                        new Restaurant(1,"Egg", 5, date2, 30),
                        new Restaurant(2,"Sandwich", 18, date3, 12),
                        new Restaurant(3,"Rice", 60, date4, 5)
                ));
    }

    @GetMapping()
    ResponseEntity<Object> getRestaurant(){
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> postNewRestaurant(@RequestBody Restaurant r) {
        restaurants.add(r);
        return new ResponseEntity<>(r, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putRestaurant(@RequestBody Restaurant r, @PathVariable int id){
        int restaurantIndex = -1;

            Restaurant restaurant = getById(restaurants, id);
            if (restaurant != null){

                restaurantIndex = restaurant.getId();
            restaurant.setName(r.getName());
            restaurant.setExpiryDate(r.getExpiryDate());
            restaurant.setPrice(r.getPrice());
            restaurant.setQuantity(r.getQuantity());

        }
        return (restaurantIndex == -1) ? postNewRestaurant(r) :  new ResponseEntity<>(r, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRestaurant(@RequestBody Restaurant r, @PathVariable int id){
        int restaurantIndex = -1;

        Restaurant restaurant = getById(restaurants, id);
        if (restaurant != null){
            restaurantIndex = restaurant.getId();
            restaurants.remove(restaurant);
        }
        return (restaurantIndex == -1) ? new ResponseEntity<>("Not Found, no restaurant with that id", HttpStatus.BAD_REQUEST) :  new ResponseEntity<>(r, HttpStatus.OK);
    }

    @PutMapping("/quantity/add/{id}/{quantity}")
    public ResponseEntity<Object> putAddQuantity(@PathVariable int id,@PathVariable int quantity ){
        int restaurantIndex = -1;

        Restaurant restaurant = getById(restaurants, id);
        if (restaurant != null){
            restaurantIndex = restaurant.getId();
            int temp = restaurant.getQuantity() + quantity;
            restaurant.setQuantity(temp);
        }
        return (restaurantIndex == -1) ? new ResponseEntity<>("Not Found, no restaurant with that id", HttpStatus.BAD_REQUEST) :   new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("/quantity/remove/{id}/{quantity}")
    public ResponseEntity<Object> putRemoveQuantity(@PathVariable int id,@PathVariable int quantity ){
        int restaurantIndex = -1;

        Restaurant restaurant = getById(restaurants, id);
        if (restaurant != null){
            restaurantIndex = restaurant.getId();
            int temp = restaurant.getQuantity() - quantity;
            restaurant.setQuantity(temp);
        }
        return (restaurantIndex == -1) ? new ResponseEntity<>("Not Found, no restaurant with that id", HttpStatus.BAD_REQUEST) :   new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> putIsExpired(@PathVariable int id) throws ParseException {
        int restaurantIndex = -1;
        Restaurant restaurant = getById(restaurants, id);
        HashMap map = new HashMap();
        if (restaurant != null){
            restaurantIndex = restaurant.getId();
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM");
            Date now = dt.parse("2022-05");

            if (restaurant.getExpiryDate().compareTo(now) < 0){

                map.put("isExpired","true");
                return new ResponseEntity<>(map, HttpStatus.OK);
            }else
                map.put("isExpired","false");
            return new ResponseEntity<>(map, HttpStatus.OK);

        }
        return (restaurantIndex == -1) ? new ResponseEntity<>("Not Found, no restaurant with that id", HttpStatus.BAD_REQUEST) :   new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    public static Restaurant getById(List<Restaurant> r, int id){
        for (Restaurant restaurant: r) {
            if (restaurant.getId() == id){
             return restaurant;
            }
        }
        return null;
    }



}
