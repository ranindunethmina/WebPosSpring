package lk.ijse.webposspring.service;

import lk.ijse.webposspring.customObj.CustomerResponse;
import lk.ijse.webposspring.dto.Impl.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO);
    void updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(CustomerDTO customerDTO);
    CustomerResponse getCustomer(String customerId);
    List<CustomerDTO> getAllCustomers();
}
