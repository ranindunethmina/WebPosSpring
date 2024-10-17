package lk.ijse.webposspring.util;

import lk.ijse.webposspring.dto.CustomerDTO;
import lk.ijse.webposspring.dto.ItemDTO;
import lk.ijse.webposspring.dto.OrderDTO;
import lk.ijse.webposspring.entity.Customer;
import lk.ijse.webposspring.entity.Item;
import lk.ijse.webposspring.entity.Order;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Component
public class Mapping {
    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.typeMap(Item.class, ItemDTO.class).addMappings(mapper -> mapper.skip(ItemDTO<String>::setImagePath));
        modelMapper.typeMap(ItemDTO.class, Item.class).addMappings(mapper -> mapper.skip(Item::setImagePath));
    }

    public CustomerDTO convertToDTO(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public Customer convertToEntity(CustomerDTO dto) {
        return modelMapper.map(dto, Customer.class);
    }

    public List<CustomerDTO> convertToDTO(List<Customer> customers) {
        return modelMapper.map(customers, new TypeToken<List<CustomerDTO>>() {}.getType());
    }


    public ItemDTO<String> convertToItemDTO(Item item) {
        return modelMapper.map(item, new TypeToken<ItemDTO<String>>() {
        }.getType());
    }

    public List<ItemDTO<String>> convertToItemDTOList(List<Item> items) {
        return modelMapper.map(items, new TypeToken<List<ItemDTO<String>>>() {
        }.getType());
    }

    public Item convertToItemEntity(ItemDTO<MultipartFile> itemDTO) {
        return modelMapper.map(itemDTO, Item.class);
    }


    public OrderDTO convertToOrderDTO(Optional<Order> orders) {
        return modelMapper.map(orders, OrderDTO.class);
    }

    public List<OrderDTO> convertToOrderDTOList(List<Order> allOrders) {
        return modelMapper.map(allOrders, new TypeToken<List<OrderDTO>>() {}.getType());
    }

}