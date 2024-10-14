package lk.ijse.webposspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
public class ItemEntity implements SuperEntity {
    @Id
    private String itemId;
    private String itemName;
    private double price;
    private int quantity;
    private String category;
    @Column(columnDefinition = "LONGTEXT")
    private String imagePath;

    @OneToMany(mappedBy = "item")
    private List<OrderDetail> orderDetails = new ArrayList<>();

}