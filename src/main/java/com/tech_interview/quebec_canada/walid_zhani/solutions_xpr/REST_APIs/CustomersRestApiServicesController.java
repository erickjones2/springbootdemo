package com.tech_interview.quebec_canada.walid_zhani.solutions_xpr.REST_APIs;

import com.tech_interview.quebec_canada.walid_zhani.solutions_xpr.Entities.Customer;
import com.tech_interview.quebec_canada.walid_zhani.solutions_xpr.Entities.CustomerSpecific;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomersRestApiServicesController {
    // Start counting from numb 2 => because I ve already created below 2 Customers with ID(s) '1 & 2' respectively
    public int idC = 2;
    private static final Map<Integer, Customer> customersRepo = new HashMap<>();

    static {
        Customer customerOne = new Customer(1, "Cristiano", "Ronaldo");
        Customer customerTwo = new Customer(2, "Lionel", "Messi");

        customersRepo.put(customerOne.getIdCustomer(), customerOne);
        customersRepo.put(customerTwo.getIdCustomer(), customerTwo);
    }

    // This => @RequestMapping(value = "/serviceDemo/customers/create", method = RequestMethod.POST) OR this one:
    @PostMapping("/serviceDemo/customers/create")
    ResponseEntity<Object> createCustomer(
        //-- With '@RequestParam' => Will send the input data from the 'Params' tab in 'Postman'
        //-- directly in the URL
        //@RequestParam("customerFN") String customerFN, @RequestParam("customerLN") String customerLN

        //-- With '@RequestBody' => Will send the input data from the 'Body' tab as 'raw / JSON' in 'Postman'
        //-- Not in the URL, but hidden with JSON requests
        @RequestBody Customer customerInput
    ) {
        idC++;
        customersRepo.put(
            //idC, new Customer(idC, customerFN, customerLN)
            idC, new Customer(idC, customerInput.getFirstName(), customerInput.getLastName())
        );
        System.out.println("customersRepo @createCustomer: "+ customersRepo);
        return new ResponseEntity<>(
            "Customer ["+ idC+ "] - @createCustomer --> is created successfully", HttpStatus.CREATED
        );
    }

    @GetMapping("/serviceDemo/customers")
    //Map<Integer, Customer> getAllCustomers() {
    Object getAllCustomers() {
        Map<Integer, Customer> customers = new HashMap<>();
        for(Map.Entry<Integer, Customer> customerEntry: customersRepo.entrySet()) {
            customers.put(customerEntry.getKey(), customerEntry.getValue());
        }
        System.out.println("@getAllCustomers: "+ customers);

        if (customers != null)
            return customers;
        else
            return new ResponseEntity<>("@getAllCustomers ==> EMPTY", HttpStatus.OK);
    }

    @GetMapping("/serviceDemo/customers/{customerID}")
    Object getCustomerById(@PathVariable Integer customerID) {
        Customer customerToFind = customersRepo.get(customerID);

        if (customerToFind != null) {
            String msgSuccess = "customerToFind["+ customerID+
                    "] - foundCustomer @getCustomerById --> FOUNDED: "+ true;
            System.out.println(msgSuccess);

            return new CustomerSpecific(
                    true,
                    customerID,
                    customerToFind.getFirstName(),
                    customerToFind.getLastName()
            );
        } else {
            String msgError = "customerToFind["+ customerID+
                    "] - foundCustomer @getCustomerById --> NOT FOUND: "+ false;
            System.out.println(msgError);
            return new ResponseEntity<>(msgError, HttpStatus.OK);
        }
    }

    @PutMapping("/serviceDemo/customers/update/{customerID}")
    ResponseEntity<String> updateCustomerById(@PathVariable Integer customerID, @RequestBody Customer customerInUpdate) {
        boolean isUpdated = false;
        String responseMsg = "";
        Customer customerToFind = customersRepo.get(customerID);

        if (customerToFind != null) {
            customerToFind.setFirstName(customerInUpdate.getFirstName());
            customerToFind.setLastName(customerInUpdate.getLastName());
            isUpdated = true;
        }
        responseMsg = "customerToFind ["+ customerID+ "] - isUpdated @updateCustomerById: "+ isUpdated;

        System.out.println(responseMsg);
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }

    @DeleteMapping("/serviceDemo/customers/delete/{customerID}")
    ResponseEntity<String> deleteCustomerById(@PathVariable Integer customerID) {
        boolean isDeleted = false;
        String responseMsg = "";
        Customer customerToFind = customersRepo.get(customerID);

        if (customerToFind != null) {
            customersRepo.remove(customerID);
            isDeleted = true;
        }
        responseMsg = "customerToFind ["+ customerID+ "] - isDeleted @deleteCustomerById: "+ isDeleted;

        System.out.println(responseMsg);
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }

}