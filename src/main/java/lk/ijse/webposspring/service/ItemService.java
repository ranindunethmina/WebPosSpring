package lk.ijse.webposspring.service;

import lk.ijse.webposspring.dto.ItemDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO<MultipartFile> itemDTO);
    void updateItem(String itemId, ItemDTO<MultipartFile> itemDTO);
    void deleteItem(String itemId);
    ItemDTO<String> getItem(String itemId);
    List<ItemDTO<String>> getAllItems();
}