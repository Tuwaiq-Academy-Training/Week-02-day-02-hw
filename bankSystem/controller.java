package com.example.triningvalid.bankSystem;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/customer")
public class controller {
    private final ArrayList<Customer> customers = new ArrayList<>();

    @GetMapping
    public ResponseEntity<ArrayList<Customer>> getCustomer(){
        return ResponseEntity.status(200).body(customers);
    }
    @PostMapping
    private ResponseEntity<Response> addCustomer(@RequestBody Customer customer){
        customers.add(customer);
        return ResponseEntity.status(200).body(new Response("New customer added",201));
    }
    @PutMapping("/{index}")
    private ResponseEntity<Response> updateCustomer(@RequestBody Customer customer,
                                                    @PathVariable Integer index){
        if (index >= customers.size() || index < 0) {
            return ResponseEntity.status(400).body(new Response("Invalid update",400));
        }
        customers.set(index,customer);
        return ResponseEntity.status(200).body(new Response("OK",200));
    }
    @DeleteMapping("/{index}")
    public ResponseEntity<Response> deleteCustomer(@PathVariable Integer index){
        if (index >= customers.size() || index <0){
            return ResponseEntity.status(400).body(new Response("Invalid request",400));
        }
        customers.remove((int)index);
        return ResponseEntity.status(200).body(new Response("Acssept request",200));
    }
    @PutMapping("/deposit")
    public ResponseEntity<Response> deposit(@RequestBody Transfer transfer){
        if (transfer.getAmount() < 0){
            return ResponseEntity.status(400).body(new Response("Invalid Request",400));
        }
        Customer currentCustomer = getCustomerFromId(transfer.getId());
        if (currentCustomer == null) {
            return ResponseEntity.status(400).body(new Response("Invalid Request",400));
        }
        Integer oldBalance = currentCustomer.getBalance();
        currentCustomer.setBalance(oldBalance + transfer.getAmount());
        return ResponseEntity.status(200).body(new Response("Accsept request",200));
    }
    // withdraw
    @PutMapping("/withdraw")
    public ResponseEntity<Response> withdraw(@RequestBody Transfer transfer){
        if (transfer.getAmount() <= 0){
            return ResponseEntity.status(400).body(new Response("Invalid Request",400));
        }
        Customer currentCustomer = getCustomerFromId(transfer.getId());
        if (currentCustomer == null) {
            return ResponseEntity.status(400).body(new Response("Invalid Request",400));
        }
        Integer oldBalance = currentCustomer.getBalance();
        currentCustomer.setBalance(oldBalance - transfer.getAmount());
        return ResponseEntity.status(200).body(new Response("Accsept request",200));
    }
    @PutMapping("/transfer/{fromId}/{toId}/{amount}")
    public ResponseEntity<Response> transfer(@PathVariable String fromId,
                                             @PathVariable String toId, @PathVariable Integer amount){
        Customer fromCustomer = getCustomerFromId(fromId);
        Customer toCustomer = getCustomerFromId(toId);
        if (fromCustomer==null || toCustomer==null){
            return ResponseEntity.status(400).body(new Response("Invalid request",400));
        }
        if(fromCustomer.getBalance()<amount){
            return ResponseEntity.status(400).body(new Response("Your request is invalid",400));
        }
        withdraw(new Transfer(fromCustomer.getId(),amount));
        deposit(new Transfer(toCustomer.getId(),amount));
        return ResponseEntity.status(200).body(new Response("Transfer Acsepted",200));
    }

    // Method for loob
    public Customer getCustomerFromId(String id){
        for (int i = 0; i < customers.size(); i++) {
            Customer currentCustomer = customers.get(i);
            if (currentCustomer.getId().equals(id)){
                return currentCustomer;
            }
        }
        return null;
    }


}
