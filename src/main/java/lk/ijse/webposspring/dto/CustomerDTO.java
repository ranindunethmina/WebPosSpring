package lk.ijse.webposspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO implements Serializable {
    private String customerId;
    private String firstName;
    private String address;
    private String mobile;
}