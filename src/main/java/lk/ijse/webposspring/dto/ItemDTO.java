package lk.ijse.webposspring.dto;

import lk.ijse.webposspring.customObj.ItemResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO implements SuperDTO, ItemResponse {
    private String itemId;
    private String itemName;
    private double price;
    private int quantity;
    private String category;
    private String imagePath;
}