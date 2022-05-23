package com.example.hw22.BankManagement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CustomersController {
    private ArrayList<CustomersModel> customer = new ArrayList<>();

    @GetMapping("bank")
    public ResponseEntity<ArrayList<CustomersModel>> getCustomer(){
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }
    @PostMapping("bank")
    public ResponseEntity<CustomersApi> addCustomer(@RequestBody CustomersModel username){
        customer.add(username);
        return ResponseEntity.status(HttpStatus.OK).body(new CustomersApi("Add Coustomer: " +username.getUsername()+" To List",200));
    }
    @PostMapping("bank/{index}/add")
    public ResponseEntity<CustomersApi> AmountSum(@PathVariable Integer index,@RequestBody CustomersModel request){
        request.getBalance().toString();
        int amount = 0;
        for (int i = 0; i < customer.size(); i++) {
            amount += customer.get(i).getBalance();
        }
        customer.add(request);
        return ResponseEntity.status(HttpStatus.OK).body(new CustomersApi("deleted!",200));
    }
    @PostMapping("bank/{index}/addCheck")
    public ResponseEntity<CustomersApi> AmountSumCheck(@PathVariable Integer index,@RequestBody CustomersModel request){
        if(request.getBalance()<0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomersApi("Sorry Your Request: BAD_REQUEST",400));
        }
        customer.set(index,request);
        return ResponseEntity.status(HttpStatus.OK).body(new CustomersApi("Bank Coustomer: " +request.getUsername()+" Edit",200));
    }

    @PostMapping("bank/{index}/withdraw")
    public ResponseEntity<CustomersApi> Withdraw(@PathVariable Integer index,@RequestBody CustomersModel request){
        request.getBalance().toString();
        int amount = 0;
        for (int i = 0; i < customer.size(); i++) {
            amount -= customer.get(i).getBalance();
        }
        customer.add(request);
        return ResponseEntity.status(HttpStatus.OK).body(new CustomersApi("add Value!",200));
    }
    @PostMapping("bank/{index}/withdrawCheck")
    public ResponseEntity<CustomersApi> WithdrawCheck(@PathVariable Integer index,@RequestBody CustomersModel request){
        if(request.getBalance()<0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomersApi("Sorry Your Request: BAD_REQUEST",400));
        }
        customer.set(index,request);
        return ResponseEntity.status(HttpStatus.OK).body(new CustomersApi("Bank Coustomer: " +request.getUsername()+" Edit",200));
    }
    @PutMapping("bank/{index}")
    public ResponseEntity<CustomersApi> editCustomer(@PathVariable Integer index,@RequestBody CustomersModel username){
       try {
           customer.set(index,username);
           return ResponseEntity.status(HttpStatus.OK).body(new CustomersApi("Edit Coustomer: " +username.getUsername()+" in List",200));
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomersApi("Your Request is bad, Sorry for you!",400));
       }
    }
    @DeleteMapping("bank/{index}")
    public ResponseEntity<CustomersApi> removeCustomer(@PathVariable Integer index){
        try {
            System.out.println(index);
            customer.remove(index);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomersApi("deleted!",200));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomersApi("Your Request is bad, Sorry for you!",400));
        }

    }
}
