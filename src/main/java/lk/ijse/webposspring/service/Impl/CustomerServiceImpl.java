package lk.ijse.webposspring.service.Impl;

import lk.ijse.webposspring.exception.CustomerAlreadyExistsException;
import lk.ijse.webposspring.repository.CustomerRepository;
import lk.ijse.webposspring.dto.CustomerDTO;
import lk.ijse.webposspring.entity.Customer;
import lk.ijse.webposspring.exception.CustomerNotFoundException;
import lk.ijse.webposspring.exception.DataPersistFailedException;
import lk.ijse.webposspring.service.CustomerService;
import lk.ijse.webposspring.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    private final Mapping mapping;

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        Optional<Customer> tempCustomer = customerRepository.findById(customerDTO.getCustomerId());
        if (tempCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists");
        } else {
            try {
                customerRepository.save(mapping.convertToEntity(customerDTO));
            } catch (Exception e) {
                throw new DataPersistFailedException("Failed to save the customer");
            }
        }
    }

    @Override
    public void updateCustomer(String customerId, CustomerDTO incomeCustomerDTO) {
        Optional<Customer> tmpNoteEntity = customerRepository.findById(customerId);
        if (!tmpNoteEntity.isPresent()){
            throw new CustomerNotFoundException("Customer not found");
        }else {
            tmpNoteEntity.get().setFirstName(incomeCustomerDTO.getFirstName());
            tmpNoteEntity.get().setAddress(incomeCustomerDTO.getAddress());
            tmpNoteEntity.get().setMobile(incomeCustomerDTO.getMobile());
        }
    }

    @Override
    public void deleteCustomer(String customerId) {
        Optional<Customer> tmpNoteEntity = customerRepository.findById(customerId);
        if (!tmpNoteEntity.isPresent()){
            throw new CustomerNotFoundException("Customer not found");
        } else {
            customerRepository.deleteById(customerId);
        }
    }

    @Override
    public CustomerDTO getCustomer(String customerId) {
        if(customerRepository.existsById(customerId)){
            return mapping.convertToDTO(customerRepository.getReferenceById(customerId));
        }else {
            throw new CustomerNotFoundException("Customer not found");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return mapping.convertToDTO(customerRepository.findAll());
    }
}