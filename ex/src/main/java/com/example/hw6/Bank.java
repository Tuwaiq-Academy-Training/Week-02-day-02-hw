package com.example.hw6;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class Bank {
    private ArrayList<Customer> bank=new ArrayList<>();

    @GetMapping("custmer")
    public ArrayList<Customer> getUsers()
    {
        return  bank;
    }

    @PostMapping("custmer")
    public ResponseEntity<ResponsApi>  adduser(@RequestBody Customer user){

    bank.add(user);
        return  ResponseEntity.status(200).body(new ResponsApi("User"+user.getUsername()+"Add user"));
    }
    @PutMapping("custmer/{index}")
    public ResponseEntity<ResponsApi> edituser(@PathVariable int index, @RequestBody Customer name){
        if (index>bank.size()-1){
            return  ResponseEntity.status(400).body(new ResponsApi("The index invalid"));
        }

        bank.set(index, name);
        return ResponseEntity.status(200).body(new ResponsApi("User"+name.getUsername()+"Add user"));}


    @DeleteMapping("custmer/{index}")
    public ResponseEntity<ResponsApi> delUser(@PathVariable int index){
        if (index>bank.size()-1){
            return  ResponseEntity.status(400).body(new ResponsApi("The index invalid"));
        }
       bank.remove(index);
        return ResponseEntity.status(200).body(new ResponsApi("User"+index+"del user"));
    }
  @PutMapping ("deposit")
    public ResponseEntity<ResponsApi> Deposit(@RequestBody Deposit deposit){
       Customer finedCustomer = null;
       if(deposit.getAmount() < 0) {
           return  ResponseEntity.status(400).body(new ResponsApi("amount must more then zero"));
       }
        for (Customer customer : bank) {
          if(customer.getId().equals(deposit.getCustomerId())) {
              finedCustomer = customer;
              break;
          }
      }
        if(finedCustomer == null) {
            return  ResponseEntity.status(400).body(new ResponsApi("invalid customer id"));
        }
        finedCustomer.setBalance(finedCustomer.getBalance() + deposit.getAmount());

        return ResponseEntity.status(200).body(new ResponsApi("your new balance is "+ finedCustomer.getBalance()));
    }
    @PutMapping ("withdraw")
    public ResponseEntity<ResponsApi> Withdraw( @RequestBody Withdraw withdraw){
        Customer finedCustomer = null;
        if(withdraw.getAmount() < 0) {
            return  ResponseEntity.status(400).body(new ResponsApi("your withdraw amount is negative"));
        }
        for (Customer customer : bank) {
            if(customer.getId().equals(withdraw.getCustomerId())) {
                finedCustomer = customer;
                break;
            }
        }
        if(finedCustomer == null) {
            return  ResponseEntity.status(400).body(new ResponsApi("invalid customer id"));
        }
        if(finedCustomer.getBalance() < withdraw.getAmount()) {
            return  ResponseEntity.status(400).body(new ResponsApi("your balance is less the the withdraw amount"));
        }
        finedCustomer.setBalance(finedCustomer.getBalance() - withdraw.getAmount());

        return ResponseEntity.status(200).body(new ResponsApi("your new balance is "+ finedCustomer.getBalance()));
    }

    @PutMapping ("transfer")
    public ResponseEntity<ResponsApi> transfer( @RequestBody Transfer transfer){
        Customer fromCustomer = null;
        Customer toCustomer = null;
        if(transfer.getAmount() < 0) {
            return  ResponseEntity.status(400).body(new ResponsApi("your amount is negative"));
        }
        for (Customer customer : bank) {
            if(customer.getId().equals(transfer.getFromCustomerId())) {
                fromCustomer = customer;
                break;
            }
        }
        if(fromCustomer == null) {
            return  ResponseEntity.status(400).body(new ResponsApi("invalid customer id"));
        }
        if(fromCustomer.getBalance() < transfer.getAmount()) {
            return  ResponseEntity.status(400).body(new ResponsApi("your balance is less the the transfer amount"));
        }
        for (Customer customer : bank) {
            if(customer.getId().equals(transfer.getToCustomerId())) {
                toCustomer = customer;
                break;
            }
        }
        if(toCustomer == null) {
            return  ResponseEntity.status(400).body(new ResponsApi("invalid to customer id"));
        }
        fromCustomer.setBalance(fromCustomer.getBalance() - transfer.getAmount());
        toCustomer.setBalance(toCustomer.getBalance() + transfer.getAmount());

        return ResponseEntity.status(200).body(new ResponsApi("your new balance is "+ fromCustomer.getBalance()));
    }

    @GetMapping("balance/{customerID}")
    public ResponseEntity<ResponsApi> getBalance(@PathVariable String customerID) {
        Customer finedCustomer = null;
        for (Customer customer : bank) {
            if(customer.getId().equals(customerID)) {
                finedCustomer = customer;
                break;
            }
        }

        if(finedCustomer == null) {
            return  ResponseEntity.status(400).body(new ResponsApi("invalid customer id"));
        }
        return ResponseEntity.status(200).body(new ResponsApi("your balance is "+ finedCustomer.getBalance()));

    }



}
