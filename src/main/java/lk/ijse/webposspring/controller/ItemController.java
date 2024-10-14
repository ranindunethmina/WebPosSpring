package lk.ijse.webposspring.controller;

import lk.ijse.webposspring.customObj.ItemResponse;
import lk.ijse.webposspring.dto.Impl.ItemDTO;
import lk.ijse.webposspring.exception.DataPersistFailedException;
import lk.ijse.webposspring.service.ItemService;
import lk.ijse.webposspring.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
public class ItemController {
    @Autowired
    private final ItemService itemService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveItem(
            @RequestPart("itemId") String itemId,
            @RequestPart("itemName") String itemName,
            @RequestPart("price") double price,
            @RequestPart("quantity") int quantity,
            @RequestPart("category") String category,
            @RequestPart("imagePath") MultipartFile imagePath)
    {
        try{
            byte[] imageByteCollection = imagePath.getBytes();
            String base64ProfilePic = AppUtil.toBase64ProfilePic(imageByteCollection);

            ItemDTO buildItemDTO = new ItemDTO();
            buildItemDTO.setItemId(itemId);
            buildItemDTO.setItemName(itemName);
            buildItemDTO.setPrice(price);
            buildItemDTO.setQuantity(quantity);
            buildItemDTO.setCategory(category);
            buildItemDTO.setImagePath(base64ProfilePic);

            itemService.saveItem(buildItemDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") String itemId) {
        try {
            itemService.deleteItem(itemId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemResponse getItem(@PathVariable("id") String itemId) {
        return itemService.getItem(itemId);
    }
    @GetMapping(value = "allItems", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }
    @PatchMapping(value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateItem(
            @RequestPart("updateItemId") String updateItemId,
            @RequestPart("updateItemName") String updateItemName,
            @RequestPart("updatePrice") double updatePrice,
            @RequestPart("updateQuantity") int updateQuantity,
            @RequestPart("updateCategory") String updateCategory,
            @RequestPart("updateImagePath") MultipartFile updateImagePath) {
        try {
            byte[] imageByteCollection = updateImagePath.getBytes();
            String base64ProfilePic = AppUtil.toBase64ProfilePic(imageByteCollection);

            var updateDTO = new ItemDTO();
            updateDTO.setItemId(updateItemId);
            updateDTO.setItemName(updateItemName);
            updateDTO.setPrice(updatePrice);
            updateDTO.setQuantity(updateQuantity);
            updateDTO.setCategory(updateCategory);
            updateDTO.setImagePath(base64ProfilePic);

            itemService.updateItem(updateDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}