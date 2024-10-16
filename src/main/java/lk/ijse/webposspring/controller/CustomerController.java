package lk.ijse.webposspring.controller;

import lk.ijse.webposspring.dto.CustomerDTO;
import lk.ijse.webposspring.exception.CustomerAlreadyExistsException;
import lk.ijse.webposspring.exception.CustomerNotFoundException;
import lk.ijse.webposspring.exception.DataPersistFailedException;
import lk.ijse.webposspring.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CustomerController {
    private final CustomerService customerService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDTO customerDTO){
        logger.info("Received request to save customer: {}", customerDTO);
        if (customerDTO == null) {
            logger.warn("Received null CustomerDTO");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            try {
                customerService.saveCustomer(customerDTO);
                logger.info("Customer saved successfully: {}", customerDTO.getCustomerId());
                return ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (CustomerAlreadyExistsException e) {
                logger.warn("Customer already exists: {}", customerDTO.getCustomerId());
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } catch (DataPersistFailedException e) {
                logger.error("Failed to persist customer data: {}", customerDTO.getCustomerId(), e);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } catch (Exception e) {
                logger.error("Unexpected error while saving customer: {}", customerDTO.getCustomerId(), e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @PatchMapping(value = "/{custId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@PathVariable ("custId") String customerId, @RequestBody CustomerDTO customerDTO) {
        logger.info("Received request to update customer: {}", customerId);
        if (customerId == null) {
            logger.warn("Received null customerId for update");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            try {
                customerService.updateCustomer(customerId, customerDTO);
                logger.info("Customer updated successfully: {}", customerId);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } catch (CustomerNotFoundException e) {
                logger.warn("Customer not found for update: {}", customerId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } catch (Exception e) {
                logger.error("Unexpected error while updating customer: {}", customerId, e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @DeleteMapping(value = "/{custId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable ("custId") String custId){
        logger.info("Received request to delete customer: {}", custId);
        try {
            customerService.deleteCustomer(custId);
            logger.info("Customer deleted successfully: {}", custId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CustomerNotFoundException e){
            logger.warn("Customer not found for deletion: {}", custId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Unexpected error while deleting customer: {}", custId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDTO>> getAllCustomer(){
        logger.info("Received request to get all customers");
        try {
            List<CustomerDTO> allCustomers = customerService.getAllCustomers();
            logger.info("Retrieved {} customers", allCustomers.size());
            return ResponseEntity.ok(allCustomers);
        } catch (Exception e) {
            logger.error("Unexpected error while retrieving all customers", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/{custId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable ("custId") String customerId){
        logger.info("Received request to search customer: {}", customerId);
        if (customerId == null) {
            logger.warn("Received null customerId for search");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            try {
                CustomerDTO customerDTO = customerService.getCustomer(customerId);
                logger.info("Customer found: {}", customerId);
                return ResponseEntity.ok(customerDTO);
            } catch (CustomerNotFoundException e) {
                logger.warn("Customer not found: {}", customerId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } catch (Exception e) {
                logger.error("Unexpected error while searching for customer: {}", customerId, e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }
}