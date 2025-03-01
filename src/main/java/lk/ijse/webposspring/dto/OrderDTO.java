package lk.ijse.webposspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO implements Serializable {
    private String orderId;
    private LocalDateTime dateAndTime;
    private String customerId;
    private double subtotal;
    private double discount;
    private double amount_payed;
    private List<OrderDetailDTO> orderDetails;
}