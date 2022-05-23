package Demo.Ex1W2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class BankManagementSystem {
    private ArrayList<Customers> customers = new ArrayList<>();

    @GetMapping("customer")
    public ResponseEntity getCustomer() {
        return ResponseEntity.status(200).body(customers);
    }

    @PostMapping("customer")
    public ResponseEntity addCustomers(@RequestBody Customers customer){
        customers.add(customer);
        return ResponseEntity.status(201).body(customer);
    }

    @PostMapping("customer")
    public ResponseEntity addCustomer(@RequestBody Customers customer){
        customers.add(customer);
        return ResponseEntity.status(201).body(customer);
    }

    @PutMapping("customer/{id}")
    public ResponseEntity updateCustomers(@PathVariable int id, @RequestBody Customers customer){
        if(id > customers.size()-1 || id < 0){
            return ResponseEntity.status(400).body(
                    "bad request, index is invalid");
        }
        customers.set(id,customer);
        return ResponseEntity.status(200).body(
                "Updated amount");
    }

    @PostMapping("customer/{id}/Deposit")
    public ResponseEntity depositAmount(@PathVariable int id, @RequestBody int Amount){
        if(id > customers.size()-1 || id < 0){
            return ResponseEntity.status(400).body(
                    "index is invalid");
        } else if (Amount < 0){
            return ResponseEntity.status(400).body(
                    "bad request, money is in the negative");
        }
        customers.get(id).setBalance(
                customers.get(id).getBalance()+Amount);
        return ResponseEntity.status(200).body(
                "Customer deposited money successfully");
    }


    @PutMapping("customer/{id}/Withdraw")
    public ResponseEntity putCustomersWithdraw(@PathVariable int id, @RequestBody int Amount) {
        if (id > customers.size()-1 || id < 0) {
            return ResponseEntity.status(400).body(
                    "bad request, balance is less the the withdraw amount");
        }
        customers.get(id).setBalance(
                customers.get(id).getBalance()- Amount);
        return ResponseEntity.status(200).body(
                "Customer withdraw money successfully");
    }


    @PutMapping("customer/{id}/{id1}/Transfermoney")
    public ResponseEntity putUserTransfer( @PathVariable int id,@PathVariable int id1 ,@RequestBody int Amount) {
        if ( id > customers.size() - 1) {
            return ResponseEntity.status(400).body("customer invalid");
        } else if (Amount < 0) {
            return ResponseEntity.status(400).body("amount is  invalid");
        } else if (Amount > customers.get(id).getBalance()) {
            return ResponseEntity.status(400).body("amount is greater than the balance");
        }else if ( id1 > customers.size() - 1 ){
            return ResponseEntity.status(400).body("Other customer is invalid");
        }
        customers.get(id).setBalance(customers.get(id).getBalance()- Amount);
        customers.get(id1).setBalance(customers.get(id1).getBalance()+ Amount);
        return ResponseEntity.status(200).body("Transfer success!");
    }


    @DeleteMapping("customer/{index}/amount")
    public ResponseEntity deleteCustomers(@PathVariable Integer id){
        if(id > customers.size()-1){
            return ResponseEntity.status(200).body (
                    "bad request status if index in invalid");
        }
        customers.remove(id);
        return ResponseEntity.status(200).body(
                "user with index "+customers.get(id).getUsername()+" deleted");
    }
}
