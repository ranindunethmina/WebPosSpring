package lk.ijse.webposspring.service.Impl;

import lk.ijse.webposspring.exception.ItemAlreadyExistsException;
import lk.ijse.webposspring.repository.ItemRepository;
import lk.ijse.webposspring.dto.ItemDTO;
import lk.ijse.webposspring.entity.Item;
import lk.ijse.webposspring.exception.DataPersistFailedException;
import lk.ijse.webposspring.exception.ItemNotFoundException;
import lk.ijse.webposspring.service.ItemService;
import lk.ijse.webposspring.util.ImageUtil;
import lk.ijse.webposspring.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    private final Mapping mapping;

    private final ImageUtil imageUtil;

    @Override
    public void saveItem(ItemDTO<MultipartFile> itemDTO) {
        Optional<Item> tempItem = itemRepository.findById(itemDTO.getItemId());
        if (tempItem.isPresent()) {
            throw new ItemAlreadyExistsException("Item already exists");
        } else {
            try {
                String imageName = imageUtil.saveImage(itemDTO.getImagePath());
                Item item = mapping.convertToItemEntity(itemDTO);
                item.setImagePath(imageName);
                itemRepository.save(item);
            } catch (Exception e) {
                throw new DataPersistFailedException("Failed to save the item");
            }
        }
    }

    @Override
    public void updateItem(String itemId, ItemDTO<MultipartFile> itemDTO) {
        Optional<Item> tempItem = itemRepository.findById(itemId);
        if (tempItem.isPresent()) {
            String imageName = tempItem.get().getImagePath();
            if (!itemDTO.getImagePath().isEmpty()) {
                imageName = imageUtil.updateImage(tempItem.get().getImagePath(), itemDTO.getImagePath());
            }
            tempItem.get().setItemName(itemDTO.getItemName());
            tempItem.get().setPrice(itemDTO.getPrice());
            tempItem.get().setQuantity(itemDTO.getQuantity());
            tempItem.get().setCategory(itemDTO.getCategory());
            tempItem.get().setImagePath(imageName);
        } else {
            throw new ItemNotFoundException("Item not found");
        }
    }

    @Override
    public void deleteItem(String itemId) {
        Optional<Item> selectedItemId = itemRepository.findById(itemId);
        if (!selectedItemId.isPresent()) {
            throw new ItemNotFoundException("Item not found");
        }else {
            imageUtil.deleteImage(selectedItemId.get().getImagePath());
            itemRepository.deleteById(itemId);
        }
    }

    @Override
    public ItemDTO<String> getItem(String itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isPresent()) {
            ItemDTO<String> itemDTO = mapping.convertToItemDTO(item.get());
            itemDTO.setImagePath(imageUtil.getImage(item.get().getImagePath()));
            return itemDTO;
        } else {
            throw new ItemNotFoundException("Item not found");
        }
    }

    @Override
    public List<ItemDTO<String>> getAllItems() {
        List<Item> items = itemRepository.findAll();
        List<ItemDTO<String>> itemDTOS = mapping.convertToItemDTOList(items);
        for (ItemDTO<String> itemDTO : itemDTOS) {
            items.stream().filter(item ->
                            item.getItemId().equals(itemDTO.getItemId()))
                    .findFirst()
                    .ifPresent(item ->
                            itemDTO.setImagePath(imageUtil.getImage(item.getImagePath())));
        }
        return itemDTOS;    }
}
