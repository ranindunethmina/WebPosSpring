package lk.ijse.webposspring.service;

import jakarta.transaction.Transactional;
import lk.ijse.webposspring.customObj.CustomerResponse;
import lk.ijse.webposspring.dao.CustomerDao;
import lk.ijse.webposspring.dto.Impl.CustomerDTO;
import lk.ijse.webposspring.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private final CustomerDao customerDao;

    @Autowired
    private final Mapping mapping;

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {

    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {

    }

    @Override
    public void deleteCustomer(CustomerDTO customerDTO) {

    }

    @Override
    public CustomerResponse getCustomer(String customerId) {
        return null;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return List.of();
    }
}
