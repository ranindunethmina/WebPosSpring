package lk.ijse.webposspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orderDetail")
public class OrderDetail implements Serializable {
    @EmbeddedId
    private OrderDetailsId orderDetailsId;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orders_Id")
    private Order orders;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_Id")
    private Item items;

    private int quantity;
    private double unitPrice;
}