package enums;

public enum CashType {
    CASH_500(500),
    CASH_100(100),
    CASH_50(50),
    CASH_20(20),
    CASH_10(10);

    public final int value;
    CashType(int value){
        this.value=value;
    }
}
