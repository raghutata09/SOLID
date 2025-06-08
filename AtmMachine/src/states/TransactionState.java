package states;

import entities.AtmMachine;
import entities.Card;
import enums.CashType;

import java.util.Map;

public class TransactionState extends ATMState {
    @Override
    public void performCashWithdrawal(AtmMachine atm, int amount) {
        Card card = atm.getCurCard();
        if (card.getAccountNo().getBalance() < amount) {
            System.out.println("Insufficient account balance.");
            atm.setAtmState("idle");
            return;
        }
        if (!atm.getAtmInventory().hasSufficientBalance(amount)) {
            System.out.println("ATM does not have enough cash.");
            atm.setAtmState("idle");
            return;
        }
        Map<CashType, Integer> cashDispensed = atm.getAtmInventory().dispenseCash(amount);
        if (cashDispensed == null) {
            System.out.println("Cannot dispense exact amount. Try a different value.");
            atm.setAtmState("idle");
            return;
        }
        card.getAccountNo().setBalance(card.getAccountNo().getBalance() - amount);
        System.out.println("Withdrawn Rs." + amount);
        System.out.println("Cash dispensed: " + cashDispensed);
        atm.setCurCard(null);
        atm.setAtmState("idle");

    }

    @Override
    public void returnCard(AtmMachine atm) {
        atm.setAtmState("idle");
        atm.setCurCard(null);
        System.out.println("Card returned successfully.");
    }
}
