package com.example.week2day2.controller;

import com.example.week2day2.model.Customer;
import com.example.week2day2.model.ResponseApi;
import com.example.week2day2.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class bankManagementSystem {

    private ArrayList<Customer> customers=new ArrayList();

    @GetMapping("/customer")
    public ResponseEntity getCustomer(){
        return ResponseEntity.status(200).body(customers);

    }

    @PostMapping("/customer")
    public ResponseEntity addCustomer(@RequestBody Customer customer){
        customers.add(customer);
        return ResponseEntity.status(201).body(new ResponseApi("Customer Added Successfully"));

    }
    @PutMapping("/customer/{index}")
    public ResponseEntity updateCustomer(@PathVariable Integer index, @RequestBody Customer customer){
        if(index>customers.size()-1 || index<0){
            return ResponseEntity.status(400).body(new ResponseApi("you have entered an invalid index :"+index));
        }
        customers.set(index,customer);
        return ResponseEntity.status(200).body(new ResponseApi("Customer has been successfully updated "));


    }
    @DeleteMapping("/customer/{index}")
    public ResponseEntity deleteCustomer(@PathVariable Integer index){

        if(index>customers.size()-1 || index<0){
            return ResponseEntity.status(400).body(new ResponseApi("you have entered an invalid index :"+index));
        }
        customers.remove((int) index);
        return ResponseEntity.status(200).body(new ResponseApi("Customer has been successfully deleted"));
    }

    @PutMapping("/customer/d/{index}")
    public ResponseEntity deposite(@PathVariable Integer index,@RequestBody Integer amount){
        if(index>customers.size()-1 || index<0){
            return ResponseEntity.status(400).body(new ResponseApi("you have entered an invalid index :"+index));
        }

        if(amount <0 ){
            return ResponseEntity.status(400).body(new ResponseApi("Invalid deposit amount"));
        }

        Customer customer=customers.get(index);
        //customers.get(index).setBalance(customer.getBalance()+amount);
        Customer tmp=new Customer(customer.getId(),customer.getUsername(), customer.getBalance()+amount);
        customers.set(index,tmp);
        return ResponseEntity.status(200).body(new ResponseApi("Amount Successfully deposited"));
    }
    @PutMapping("/customer/w/{index}")
    public ResponseEntity withdraw(@PathVariable Integer index,@RequestBody Integer amount){
        if(index>customers.size()-1 || index<0){
            return ResponseEntity.status(400).body(new ResponseApi("you have entered an invalid index :"+index));
        }

        if(amount <0|| amount>customers.get(index).getBalance()){
            return ResponseEntity.status(400).body(new ResponseApi("Invalid deposit amount"));
        }

        Customer customer=customers.get(index);
        //customers.get(index).setBalance(customer.getBalance()-amount);
        Customer tmp=new Customer(customer.getId(),customer.getUsername(), customer.getBalance()-amount);
        customers.set(index,tmp);
        return ResponseEntity.status(200).body(new ResponseApi("Amount Successfully deposited"));
    }
    @PutMapping("/customer/t/{from}/{to}")
    public ResponseEntity transfer(@PathVariable Integer from, @PathVariable Integer to,@RequestBody Integer amount){
        if(to>customers.size()-1 || to<0){
            return ResponseEntity.status(400).body(new ResponseApi("you have entered an invalid index :"+to));
        }
        if(amount <0|| amount>customers.get(from).getBalance()){
            return ResponseEntity.status(400).body(new ResponseApi("Invalid amount"));
        }

        customers.get(to).setBalance(customers.get(to).getBalance()+amount);
        customers.get(from).setBalance(customers.get(from).getBalance()-amount);

        return ResponseEntity.status(200).body(new ResponseApi("Amount Successfully Transferred"));


    }
    @GetMapping("/customer/{index}")
    public ResponseEntity getBalance(@PathVariable Integer index){
        if(index>customers.size()-1 || index<0){
            return ResponseEntity.status(400).body(new ResponseApi("you have entered an invalid index :"+index));
        }
        Customer tmp_customer=customers.get(index);
        return ResponseEntity.status(200).body(new ResponseApi(tmp_customer.getUsername()+"'s balance is : "+tmp_customer.getBalance()));

    }





}
