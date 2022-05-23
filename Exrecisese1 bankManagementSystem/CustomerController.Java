package com.example.springday2ex1.Controllers;

import com.example.springday2ex1.model.Customer;
import com.example.springday2ex1.model.CustomerApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springday2ex1.model.Deposit;
import com.example.springday2ex1.model.Withdraw;
import com.example.springday2ex1.model.Transfer;



import java.util.ArrayList;


@RestController
public class CustomerController {

    private ArrayList<Object> customers = new ArrayList<>();

    @GetMapping("customer")
    public ResponseEntity<ArrayList<Object>> getCustomers() {
        return ResponseEntity.status(200).body(customers);
    }

    @GetMapping("/customer/{index}")
    public ResponseEntity getBalance(@PathVariable Integer index){
        if(index>customers.size()-1 || index < 0){
            return ResponseEntity.status(400).body(new CustomerApi("you have entered an invalid index :"+index));
        }
        Object tmp_customer=customers.get(index);
        return ResponseEntity.status(200).body(new CustomerApi(tmp_customer.getUsername()+"'s balance is : "+tmp_customer.getBalance()));


    @PostMapping("customer")
    public ResponseEntity<CustomerApi> addCustomer(@RequestBody Customer  Object customer) {
        customers.add(customer);
        return ResponseEntity.status(201).body(new CustomerApi("Customer Added Successfully"));
    }

    @PutMapping("customer/{index}")
    public ResponseEntity updateCustomer(@PathVariable Integer index, @RequestBody Customer customer){
        if(index > customers.size()-1 || index < 0){
            return ResponseEntity.status(400).body(new CustomerApi("you have entered an invalid index :"+index));
        }
        customers.set(index,customer);
        return ResponseEntity.status(200).body(new CustomerApi("Customer has been successfully updated "));

    }


    @PutMapping("Deposit/{index}")
    public ResponseEntity addDeposite(@PathVariable Integer index,@RequestBody Integer amount){
        if(index>customers.size()-1 || index < 0){
            return ResponseEntity.status(400).body(new CustomerApi("entered an invalid index :" + index));
        }

        if(amount < 0 ){
            return ResponseEntity.status(400).body(new CustomerApi("Invalid deposit"));
        }

        Object customer=customers.get(index);
        Customer tmp = new Customer(customer.getID(),customer.getUsername(), customer.getBalance()+amount);
        customers.set(index,tmp);
        return ResponseEntity.status(200).body(new CustomerApi("Amount deposited"));
    }


    @PutMapping("Withdraw/{index}")
    public ResponseEntity addWithdraw(@PathVariable Integer index,@RequestBody Integer amount) {
        if (index > customers.size() - 1 || index < 0) {
            return ResponseEntity.status(400).body(new CustomerApi("Entered an invalid index :" + index));
        }

        if (amount < 0 || amount > customers.get(index).getBalance()) {
            return ResponseEntity.status(400).body(new CustomerApi("Invalid deposit"));
        }
        Object customer=customers.get(index);
        Customer tmp = new Customer(customer.getID(),customer.getUsername(), customer.getBalance()-amount);
        customers.set(index,tmp);
        return ResponseEntity.status(200).body(new CustomerApi("Amount deposited"));
    }

    @PutMapping("Transfer/{index}")
    public ResponseEntity addTransfer(@PathVariable Integer Cof, @PathVariable Integer Cin,@RequestBody Integer amount){
        if(Cin > customers.size()-1 || Cin < 0){
            return ResponseEntity.status(400).body(new CustomerApi("you have entered an invalid index :"+ to));
        }
        if(amount < 0|| amount >customers.get(Cof).getBalance()){
            return ResponseEntity.status(400).body(new CustomerApi("Invalid amount"));
        }

        customers.get(Cin).setBalance(customers.get(Cin).getBalance() + amount);
        customers.get(Cof).setBalance(customers.get(Cof).getBalance() - amount);

        return ResponseEntity.status(200).body(new CustomerApi("Amount Transfer"));

    }
    
    @DeleteMapping("customer/{index}")
        public ResponseEntity deleteCustomer(@PathVariable Integer index){

            if(index>customers.size()-1 || index<0){
                return ResponseEntity.status(400).body(new CustomerApi("Entered an invalid index :"+index));
            }
            customers.remove((int) index);
            return ResponseEntity.status(200).body(new CustomerApi("deleted Customer"));
        }
    }
}