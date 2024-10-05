package lk.ijse.webposspring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orderDetail")
public class OrderDetailEntity implements Serializable {
    @Id
    private String orderId;
    private String itemId;
    private int quantity;
    private double untPrice;
}
