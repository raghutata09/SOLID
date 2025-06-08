package entities;

public class Account {

    private String accountNo;
    private int balance;
    private String userName;
    private int phoneNo;

    public Account(String accountNo, int balance, String userName, int phoneNo) {
        this.accountNo = accountNo;
        this.balance = balance;
        this.userName = userName;
        this.phoneNo = phoneNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }
}
