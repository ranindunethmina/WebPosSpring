package lk.ijse.webposspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDTO implements SuperDTO {
    private String orderId;
    private String itemId;
    private int quantity;
    private double price;
}