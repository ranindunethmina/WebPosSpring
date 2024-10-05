package lk.ijse.webposspring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orderDetail")
public class OrderDetailEntity implements SuperEntity {
    @Id
    private String orderId;
    private String itemId;
    private int quantity;
    private double untPrice;
}
