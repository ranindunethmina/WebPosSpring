package lk.ijse.webposspring.util;

import lk.ijse.webposspring.dto.CustomerDTO;
import lk.ijse.webposspring.dto.ItemDTO;
import lk.ijse.webposspring.entity.Customer;
import lk.ijse.webposspring.entity.Item;
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
    public CustomerDTO convertToDTO(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public Customer convertToEntity(CustomerDTO dto) {
        return modelMapper.map(dto, Customer.class);
    }

    public List<CustomerDTO> convertToDTO(List<Customer> customers) {
        return modelMapper.map(customers, new TypeToken<List<CustomerDTO>>() {}.getType());
    }

    //matters of ItemEntity and DTO
    public ItemDTO convertToItemDTO(Item item) {
        return modelMapper.map(item, ItemDTO.class);
    }

    public Item convertToItemEntity(ItemDTO dto) {
        return modelMapper.map(dto, Item.class);
    }

    public List<ItemDTO> convertToItemDTO(List<Item> items) {
        return modelMapper.map(items, new TypeToken<List<ItemDTO>>() {}.getType());
    }

}