package com.example.week2day2.controller;

import com.example.week2day2.model.ResponseApi;
import com.example.week2day2.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class testController {
    private ArrayList<User> users=new ArrayList();

    @GetMapping("/test")
    public ResponseEntity getUser(){
        return ResponseEntity.status(200).body(users);

    }
    //ResponseEntity.status(status_code).body(message);
    //HttpStatus.BAD...
    @PostMapping("/test")
    public ResponseEntity addUser(@RequestBody User user){
        users.add(user);
        return ResponseEntity.status(200).body(new ResponseApi("User Added Successfully"));

    }
    @PutMapping("/test/{index}")
    public ResponseEntity updateUser(@PathVariable Integer index, @RequestBody User user){
        if(index>users.size()-1 || index<0){
            return ResponseEntity.status(404).body(new ResponseApi("you have entered a wrong index :"+index));
        }
        users.set(index,user);
        return ResponseEntity.status(200).body(new ResponseApi("User Has Been Updated"));


    }
    @DeleteMapping("/test/{index}")
    public ResponseEntity deleteUser(@PathVariable Integer index){

        if(index>users.size()-1 || index<0){
            return ResponseEntity.status(404).body(new ResponseApi("you have entered a wrong index :"+index));
        }
        users.remove(index);
        return ResponseEntity.status(200).body(new ResponseApi("User Has Been Successfully Deleted"));
    }
}
