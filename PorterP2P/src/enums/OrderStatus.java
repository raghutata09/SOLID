package enums;

public enum OrderStatus {
    CREATED, // When model.Order is created
    ASSIGNED, // When model.Order is assigned to a delivery person
    PICKED_UP, // When model.Order is picked up by the delivery guy
    DELIVERED, // When model.Order is delivered to the customer
    CANCELLED, // When model.Order is cancelled by the customer or system
    EXPIRED // When model.Order is not delivered within a certain time frame
}