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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController {
    @Autowired
    private final CustomerService customerService;

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDTO customerDTO){
        logger.info("Request to save customer {}", customerDTO);
        if(customerDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            try {
                customerService.saveCustomer(customerDTO);
                logger.info("Successfully saved customer: {}", customerDTO);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (DataPersistFailedException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PatchMapping(value = "/{custId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@PathVariable ("custId") String custId, @RequestBody CustomerDTO customerDTO){
        try {
            if (customerDTO == null && custId == null || custId.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            customerService.updateCustomer(custId, customerDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CustomerNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{custId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable ("custId") String custId){
        logger.info("Request to delete customer {}", custId);
        try {
            customerService.deleteCustomer(custId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CustomerNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "allCust", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getAllCustomer(){
        logger.info("Request to get all customers");
        return customerService.getAllCustomers();
    }

    @GetMapping(value = "/{custId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerResponse getCustomer(@PathVariable ("custId") String custId){
        if(custId.isEmpty() || custId == null){
            return new CustomerErrorResponse(1,"Not valid note id");
        }
        logger.info("Request to get customer {}", custId);
        return customerService.getCustomer(custId);
    }
}