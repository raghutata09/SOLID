package model;

import utils.Util;

public class User {
    private String id;
    private String name;
    private String phoneNo;
    private String emai;

    public User(String name, String phoneNo, String emai) {
        this.id= Util.generateId("user");
        this.name = name;
        this.phoneNo = phoneNo;
        this.emai = emai;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getEmai() {
        return emai;
    }
}
