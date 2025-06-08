package entities;

import java.util.HashMap;
import java.util.Map;
import enums.CashType;

import java.util.concurrent.ConcurrentHashMap;

public class ATMInventory {

    private Map<CashType, Integer> cashTypeIntegerMap;

    ATMInventory(){
        this.cashTypeIntegerMap = new ConcurrentHashMap<>();
        for (CashType type : CashType.values()) {
            cashTypeIntegerMap.put(type, 0);
        }
        initializeInventory();
    }

    public void addMoney(CashType cashType, Integer qty){
        cashTypeIntegerMap.put(cashType,cashTypeIntegerMap.get(cashType)+qty);
    }

    private void initializeInventory() {
        // Initialize with some cash
        cashTypeIntegerMap.put(CashType.CASH_500, 10);
        cashTypeIntegerMap.put(CashType.CASH_100, 10);
    }

    private int getTotalCashInAtm(){
        int total = 0;
        for(CashType cashType : cashTypeIntegerMap.keySet()){
            total += cashType.value * cashTypeIntegerMap.get(cashType);
        }
        return total;
    }

    public boolean hasSufficientBalance(int amntToWithdraw){
        int total = getTotalCashInAtm();
        return amntToWithdraw<=total;
    }

    public Map<CashType,Integer> dispenseCash(int amount){
        if(!hasSufficientBalance(amount)){
            return null;
        }

        Map<CashType,Integer> dispensedCash = new HashMap<>();
        int remainingAmount = amount;
        for (CashType cashType : CashType.values()) {
            int count = Math.min(
                    remainingAmount / cashType.value, cashTypeIntegerMap.get(cashType));
            if (count > 0) {
                dispensedCash.put(cashType, count);
                remainingAmount -= count * cashType.value;
                cashTypeIntegerMap.put(cashType, cashTypeIntegerMap.get(cashType) - count);
            }
        }
        // If we couldn't make exact change
        if (remainingAmount > 0) {
            // Rollback the transaction
            for (Map.Entry<CashType, Integer> entry : dispensedCash.entrySet()) {
                cashTypeIntegerMap.put(entry.getKey(),
                        cashTypeIntegerMap.get(entry.getKey()) + entry.getValue());
            }
            return null;
        }
        return dispensedCash;
    }
}
