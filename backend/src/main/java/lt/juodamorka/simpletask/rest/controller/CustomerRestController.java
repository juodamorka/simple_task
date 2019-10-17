package lt.juodamorka.simpletask.rest.controller;

import lt.juodamorka.simpletask.entity.Customer;
import lt.juodamorka.simpletask.repository.CustomerRepository;
import lt.juodamorka.simpletask.rest.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/customer")
public class CustomerRestController {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ValidationService validationService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(produces = "application/json")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        if (validationService.isInputValid(customer)) {
            customerRepository.save(customer);
            logger.info("A new customer " + customer.getName() + " " + customer.getSurname() + " has been created.");

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        logger.log(Level.SEVERE, "Invalid customer");

        return ResponseEntity.badRequest().build();
    }
}
