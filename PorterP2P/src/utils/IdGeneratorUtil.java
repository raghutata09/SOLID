package utils;

import java.util.UUID;

public class IdGeneratorUtil {
    public static String generateCustomerId() {
        return "CU_" + UUID.randomUUID();
    }

    public static String generateDriverId() {
        return "DR_" + UUID.randomUUID();
    }

    public static String generateOrderId() {
        return "OR_" + UUID.randomUUID();
    }
}
