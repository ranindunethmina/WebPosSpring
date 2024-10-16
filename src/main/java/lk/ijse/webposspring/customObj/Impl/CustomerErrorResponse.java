package lk.ijse.webposspring.customObj.Impl;

import lk.ijse.webposspring.customObj.CustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerErrorResponse implements Serializable, CustomerResponse {
    private int errorCode;
    public String errorMessage;
}