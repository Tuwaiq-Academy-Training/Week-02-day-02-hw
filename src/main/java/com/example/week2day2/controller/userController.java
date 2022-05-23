package com.example.week2day2.controller;

import com.example.week2day2.model.ResponseApi;
import com.example.week2day2.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class userController {
    private ArrayList<User>users=new ArrayList();

    @GetMapping("/user")
    public ArrayList<User> getUser(){
        return users;
    }
    @PostMapping("/user")
    public ResponseApi addUser(@RequestBody User user){
    users.add(user);
    return new ResponseApi("User Added Successfully");
    }
    @PutMapping("/user/{index}")
    public ResponseApi updateUser(@PathVariable int index,@RequestBody User user){
    users.set(index,user);
    return new ResponseApi("User Has Been Updated");

    }
    @DeleteMapping("/user/{index}")
    public ResponseApi deleteUser(@PathVariable int index){
        users.remove(index);
        return new ResponseApi("User Has Been Successfully Deleted");
    }
}
