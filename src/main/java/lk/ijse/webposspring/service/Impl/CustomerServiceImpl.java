package lk.ijse.webposspring.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.webposspring.customObj.Impl.CustomerErrorResponse;
import lk.ijse.webposspring.customObj.CustomerResponse;
import lk.ijse.webposspring.dao.CustomerDao;
import lk.ijse.webposspring.dto.CustomerDTO;
import lk.ijse.webposspring.entity.Customer;
import lk.ijse.webposspring.exception.CustomerNotFoundException;
import lk.ijse.webposspring.exception.DataPersistFailedException;
import lk.ijse.webposspring.service.CustomerService;
import lk.ijse.webposspring.util.AppUtil;
import lk.ijse.webposspring.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao customerDao;

    private final Mapping mapping;

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        customerDTO.setCustomerId(AppUtil.createCustomerID());
        var custEntity = mapping.convertToEntity(customerDTO);
        var saveCustomer = customerDao.save(custEntity);
        if (saveCustomer == null) {
            throw new DataPersistFailedException("Cannot save customer");
        }
    }

    @Override
    public void updateCustomer(String customerId, CustomerDTO incomeCustomerDTO) {
        Optional<Customer> tmpNoteEntity = customerDao.findById(customerId);
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
        Optional<Customer> tmpNoteEntity = customerDao.findById(customerId);
        if (!tmpNoteEntity.isPresent()){
            throw new CustomerNotFoundException("Customer not found");
        } else {
            customerDao.deleteById(customerId);
        }
    }

    @Override
    public CustomerResponse getCustomer(String customerId) {
        if(customerDao.existsById(customerId)){
            return mapping.convertToDTO(customerDao.getReferenceById(customerId));
        }else {
            return new CustomerErrorResponse(0,"Customer not found");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return mapping.convertToDTO(customerDao.findAll());
    }
}