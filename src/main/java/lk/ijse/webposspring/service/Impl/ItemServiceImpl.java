package lk.ijse.webposspring.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.webposspring.customObj.Impl.ItemErrorResponse;
import lk.ijse.webposspring.customObj.ItemResponse;
import lk.ijse.webposspring.dao.ItemDao;
import lk.ijse.webposspring.dto.ItemDTO;
import lk.ijse.webposspring.entity.Item;
import lk.ijse.webposspring.exception.DataPersistFailedException;
import lk.ijse.webposspring.service.ItemService;
import lk.ijse.webposspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDao itemDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        Item savedItem = itemDao.save(mapping.convertToItemEntity(itemDTO));
        if (savedItem == null) {
            throw new DataPersistFailedException("Cannot save item");
        }
    }

    @Override
    public void updateItem(ItemDTO itemDTO) {
        Optional<Item> tmpItem = itemDao.findById(itemDTO.getItemId());
        if (!tmpItem.isPresent()) {
            throw new DataPersistFailedException("Item not found");
        }else {
            tmpItem.get().setItemName(itemDTO.getItemName());
            tmpItem.get().setPrice(itemDTO.getPrice());
            tmpItem.get().setQuantity(itemDTO.getQuantity());
            tmpItem.get().setCategory(itemDTO.getCategory());
            tmpItem.get().setImagePath(itemDTO.getImagePath());
        }
    }

    @Override
    public void deleteItem(String itemId) {
        Optional<Item> selectedItemId = itemDao.findById(itemId);
        if (!selectedItemId.isPresent()) {
            throw new DataPersistFailedException("Item not found");
        }else {
            itemDao.deleteById(itemId);
        }
    }

    @Override
    public ItemResponse getItem(String itemId) {
        if (itemDao.existsById(itemId)) {
            Item itemEntityByItemId = itemDao.getItemEntitiesByItemId(itemId);
            return mapping.convertToItemDTO(itemEntityByItemId);
        }else {
            return new ItemErrorResponse(0,"Item not found");
        }
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return mapping.convertToItemDTO(itemDao.findAll());
    }
}
