package lk.ijse.webposspring.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String createCustomerID(){
        return "CUST-"+ UUID.randomUUID();
    }

    public static String toBase64ProfilePic(byte [] imagePath){
        return Base64.getEncoder().encodeToString(imagePath);
    }

}