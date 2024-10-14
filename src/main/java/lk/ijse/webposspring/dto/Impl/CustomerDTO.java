package lk.ijse.webposspring.dto.Impl;

import lk.ijse.webposspring.customObj.CustomerResponse;
import lk.ijse.webposspring.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO implements SuperDTO, CustomerResponse {
    private String customerId;
    private String firstName;
    private String address;
    private String mobile;
    private List<String> orderIds;
}