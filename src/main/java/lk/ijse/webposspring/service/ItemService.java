package lk.ijse.webposspring.service;

import lk.ijse.webposspring.customObj.ItemResponse;
import lk.ijse.webposspring.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    void updateItem(ItemDTO itemDTO);
    void deleteItem(String itemId);
    ItemResponse getItem(String itemId);
    List<ItemDTO> getAllItems();
}
