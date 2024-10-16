package lk.ijse.webposspring.controller;

import lk.ijse.webposspring.customObj.Impl.CustomerErrorResponse;
import lk.ijse.webposspring.customObj.CustomerResponse;
import lk.ijse.webposspring.dto.CustomerDTO;
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
        if(customerDTO == null){
            logger.warn("Received null CustomerDTO");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            try {
                customerService.saveCustomer(customerDTO);
                logger.info("Customer saved successfully: {}", customerDTO.getCustomerId());
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (DataPersistFailedException e) {
                logger.error("Failed to persist customer data: {}", customerDTO.getCustomerId(), e);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                logger.error("Unexpected error while saving customer: {}", customerDTO.getCustomerId(), e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PatchMapping(value = "/{custId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@PathVariable ("custId") String custId, @RequestBody CustomerDTO customerDTO) {
        logger.info("Received request to update customer: {}", custId);
        if (custId == null) {
            logger.warn("Received null customerId for update");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            try {
                customerService.updateCustomer(custId, customerDTO);
                logger.info("Customer updated successfully: {}", custId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }catch(CustomerNotFoundException e){
                logger.warn("Customer not found for update: {}", custId);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }catch(Exception e){
                logger.error("Unexpected error while updating customer: {}", custId, e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
    public List<CustomerDTO> getAllCustomer(){
        logger.info("Received request to get all customers");
        return customerService.getAllCustomers();
    }

    @GetMapping(value = "/{custId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerResponse getCustomer(@PathVariable ("custId") String custId){
        logger.info("Received request to search customer: {}", custId);
        if(custId.isEmpty() || custId == null){
            logger.warn("Received null customerId for search");
            return new CustomerErrorResponse(1,"Not valid note id");
        }
        logger.info("Customer found: {}", custId);
        return customerService.getCustomer(custId);
    }
}