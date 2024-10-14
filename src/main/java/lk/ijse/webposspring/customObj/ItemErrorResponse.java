package lk.ijse.webposspring.customObj;

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