package lk.ijse.webposspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO implements Serializable {
    private String itemId;
    private String itemName;
    private double price;
    private int quantity;
    private String category;
    private String imagePath;
}