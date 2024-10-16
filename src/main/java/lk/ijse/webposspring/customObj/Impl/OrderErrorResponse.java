package lk.ijse.webposspring.customObj.Impl;

import lk.ijse.webposspring.customObj.OrderResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderErrorResponse implements Serializable, OrderResponse {
    private int errorCode;
    public String errorMessage;
}