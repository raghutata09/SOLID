package model;

import utils.IdGeneratorUtil;

// model.Customer class extending model.User
public class Customer extends User {
    public Customer(String name, String phoneNumber) {
        super(IdGeneratorUtil.generateCustomerId(),name, phoneNumber);
    }
}