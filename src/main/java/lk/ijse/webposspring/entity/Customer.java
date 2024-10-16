package lk.ijse.webposspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class Customer implements SuperEntity {
    @Id
    private String customerId;
    private String firstName;
    private String address;
    private String mobile;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}