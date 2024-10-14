package lk.ijse.webposspring.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.webposspring.customObj.ItemResponse;
import lk.ijse.webposspring.dao.ItemDao;
import lk.ijse.webposspring.dto.Impl.ItemDTO;
import lk.ijse.webposspring.service.ItemService;
import lk.ijse.webposspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDao itemDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveItem(ItemDTO itemDTO) {

    }

    @Override
    public void updateItem(String itemId, ItemDTO itemDTO) {

    }

    @Override
    public void deleteItem(String itemId) {

    }

    @Override
    public ItemResponse getItem(String itemId) {
        return null;
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return List.of();
    }
}
