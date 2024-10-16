package lk.ijse.webposspring.customObj.Impl;

import lk.ijse.webposspring.customObj.OrderDetailResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailErrorResponse implements Serializable, OrderDetailResponse {
    private int errorCode;
    public String errorMessage;
}