package entities;
public class Card {

    private int cardNo;
    private int expiryDate;
    private int cvv;
    private String holderName;
    private int pin;
    private Account account;

    public Card(int cardNo, int expiryDate, int cvv, String holderName, int pin, Account account) {
        this.cardNo = cardNo;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.holderName = holderName;
        this.pin = pin;
        this.account = account;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public int getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(int expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCvv() {
        return cvv;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public Account getAccountNo() {
        return account;
    }

    public void setAccountNo(Account account) {
        this.account = account;
    }
}
