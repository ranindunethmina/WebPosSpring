package lk.ijse.webposspring.service;

import lk.ijse.webposspring.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO);
    void updateCustomer(String customerId, CustomerDTO customerDTO);
    void deleteCustomer(String customerId);
    CustomerDTO getCustomer(String customerId);
    List<CustomerDTO> getAllCustomers();
}
