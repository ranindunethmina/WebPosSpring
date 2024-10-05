package lk.ijse.webposspring.util;

import java.util.UUID;

public class AppUtil {
    public static String createCustomerID(){
        return "CUST-"+ UUID.randomUUID();
    }
}
