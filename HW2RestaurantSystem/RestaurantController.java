package com.example.hw2_02_02.controller;

public class RestaurantController {

    public class BankController {



        private final ArrayList<Customer> customers = new ArrayList();


        @GetMapping
        public ResponseEntity<ArrayList<Customer>> getCustomers() {
            return ResponseEntity.status(200).body(customers);
        }


        @PostMapping
        public ResponseEntity<ApiResponse> addCustomer(@RequestBody Customer customer) {
            customers.add(customer);
            return ResponseEntity.status(201).body(new ApiResponse("New customer added", 201));
        }

        @PutMapping("/{index}")
        public ResponseEntity<ApiResponse> updateCustomer(@RequestBody Customer customer,
                                                          @PathVariable Integer index) {
            if (index >= customers.size() || index < 0) {
                return ResponseEntity.status(400).body(new ApiResponse("Invalid index", 400));
            }
            customers.set(index, customer);
            return ResponseEntity.status(200).body(new ApiResponse("Customer updated", 200));
        }

        @DeleteMapping("/{index}")
        public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Integer index) {
            if (index >= customers.size() || index < 0) {
                return ResponseEntity.status(400).body(new ApiResponse("Invalid index", 400));
            }
            customers.remove((int) index);
            return ResponseEntity.status(200).body(new ApiResponse("Customer deleted", 200));
        }


        @PutMapping("/deposit")
        public ResponseEntity<ApiResponse> deposit(@RequestBody Transfer transfer) {
            if (transfer.getAmount() < 0) {
                return ResponseEntity.status(400).body(new ApiResponse("Invalid amount", 400));
            }
            Customer currentCustomer = getCustomerFromId(transfer.getId());
            if (currentCustomer == null) {
                return ResponseEntity.status(400).body(new ApiResponse("Invalid id", 400));
            }
            Integer oldBalance = currentCustomer.getBalance();
            currentCustomer.setBalance(oldBalance + transfer.getAmount());
            return ResponseEntity.status(200).body(new ApiResponse("Deposit completed", 200));
        }

        @PutMapping("/withdraw")
        public ResponseEntity<ApiResponse> withdraw(@RequestBody Transfer transfer) {
            if (transfer.getAmount() <= 0) {
                return ResponseEntity.status(400).body(new ApiResponse("Invalid amount", 400));
            }

            Customer currentCustomer = getCustomerFromId(transfer.getId());
            if (currentCustomer == null) {
                return ResponseEntity.status(400).body(new ApiResponse("Invalid id", 400));
            }
            if (currentCustomer.getBalance() < transfer.getAmount()) {
                return ResponseEntity.status(400).body(new ApiResponse(
                        "You don't have enough balance to complete the withdraw", 400));
            }
            Integer oldBalance = currentCustomer.getBalance();
            currentCustomer.setBalance(oldBalance - transfer.getAmount());
            return ResponseEntity.status(200).body(new ApiResponse("Withdraw completed", 200));

        }

        @PutMapping("/transfer/{fromId}/{toId}/{amount}")
        public ResponseEntity<ApiResponse> transfer(@PathVariable String fromId,
                                                    @PathVariable String toId, @PathVariable Integer amount) {

            Customer fromCustomer=getCustomerFromId(fromId);
            Customer toCustomer=getCustomerFromId(toId);

            if(fromCustomer==null || toCustomer==null){
                return ResponseEntity.status(400).body(new ApiResponse("Invalid id", 400));
            }

            if(fromCustomer.getBalance()<amount){
                return ResponseEntity.status(400).body(new ApiResponse(
                        "You don't have enough balance to complete the transfer", 400));
            }

            withdraw(new Transfer(fromCustomer.getId(),amount));
            deposit(new Transfer(toCustomer.getId(),amount));

            return ResponseEntity.status(200).body(new ApiResponse("Transfer completed", 200));
        }
    }
}
