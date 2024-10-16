package lk.ijse.webposspring.customObj.Impl;

import lk.ijse.webposspring.customObj.ItemResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemErrorResponse implements Serializable, ItemResponse {
    private int errorCode;
    public String errorMessage;
}