package model;

import enums.ItemType;
import enums.OrderStatus;
import utils.IdGeneratorUtil;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class Order {
    private final String orderId;
    private final Customer customer;
    private final String srcAddress;
    private final String destAddress;
    private final ItemType itemType;
    private final Double weight;
    private final LocalDateTime createdAt;
    private Driver assignedDriver;
    private final AtomicReference<OrderStatus> status;

    public Order(Customer customer, String srcAddress, String destAddress, ItemType itemType, Double weight) {
        this.orderId = IdGeneratorUtil.generateOrderId();
        this.customer = customer;
        this.srcAddress = srcAddress;
        this.destAddress = destAddress;
        this.itemType = itemType;
        this.weight = weight;
        this.createdAt = LocalDateTime.now();
        this.status = new AtomicReference<>(OrderStatus.CREATED);
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getSrcAddress() {
        return srcAddress;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public Double getWeight() {
        return weight;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public OrderStatus getStatus() {
        return status.get();
    }

    public void setStatus(OrderStatus newStatus) {
        status.set(newStatus);
    }

    public Driver getAssignedDriver() {
        return assignedDriver;
    }

    public void assignDriver(Driver driver) {
        this.assignedDriver = driver;
        setStatus(OrderStatus.ASSIGNED);
    }
}