package utils;

import java.util.UUID;

public class Util {

    public static String generateId(String id){
        if(id.equalsIgnoreCase("user")){
            return "U-"+ UUID.randomUUID().toString();
        }else if(id.equalsIgnoreCase("order")){
            return "O-"+ UUID.randomUUID().toString();
        }
        else if(id.equalsIgnoreCase("product")){
            return "P-"+ UUID.randomUUID().toString();
        }
        else if(id.equalsIgnoreCase("bill")){
            return "B-"+ UUID.randomUUID().toString();
        }
        else {
            return UUID.randomUUID().toString();
        }
    }

    public static void printMsg(String string){
        System.out.println(string);
    }
}
