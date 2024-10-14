package lk.ijse.webposspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order")
public class OrderEntity implements SuperEntity {
    @Id
    private String orderId;
    private LocalDateTime dateAndTime;
    private String customerId;
    private double subtotal;
    private double discount;
    private double amount_payed;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();

}
