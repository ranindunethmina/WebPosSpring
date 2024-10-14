package lk.ijse.webposspring.util;

import lk.ijse.webposspring.dto.Impl.CustomerDTO;
import lk.ijse.webposspring.entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    //matters of CustomerEntity and DTO
    public CustomerDTO convertToDTO(CustomerEntity customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public CustomerEntity convertToEntity(CustomerDTO dto) {
        return modelMapper.map(dto, CustomerEntity.class);
    }

    public List<CustomerDTO> convertToDTO(List<CustomerEntity> customers) {
        return modelMapper.map(customers, new TypeToken<List<CustomerDTO>>() {}.getType());
    }
}
