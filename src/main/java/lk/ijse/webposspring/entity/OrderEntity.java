package lk.ijse.webposspring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order")
public class OrderEntity implements Serializable {
    @Id
    private String orderId;
    private LocalDateTime dateAndTime;
    private String customerId;
    private double subtotal;
    private double discount;
    private double amount_payed;
}
