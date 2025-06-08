package entities;

import enums.CashType;
import enums.TransactionType;
import factory.AtmStateFactory;
import states.ATMState;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AtmMachine {

    private static AtmMachine atmMachine;
    private ATMState atmState;
    private AtmStateFactory atmStateFactory;
    private ATMInventory atmInventory;
    private Card curCard;
    private Map<String, Account> accountMap;
    private TransactionType transactionType;

    private AtmMachine() {
        this.atmStateFactory = new AtmStateFactory();
        this.atmState = atmStateFactory.getAtmState("idle");
        this.atmInventory = new ATMInventory();
        this.accountMap = new ConcurrentHashMap<>();
        System.out.println("ATM is initialised");
    }

    public static AtmMachine getInstance() {
        if (atmMachine == null) {
            atmMachine = new AtmMachine();
        }
        return atmMachine;
    }

    public void addMoneyInAtm(CashType cashType, int qty) {
        atmInventory.addMoney(cashType, qty);
    }

    public boolean insertCard(Card card) {
        if (!accountMap.containsKey(card.getAccountNo().getAccountNo())) {
            System.out.println("You are inserting a wrong bank card");
            return false;
        }
        this.curCard = card;
        return true;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setAtmState(String stateName) {
        System.out.println("ATM state changing to: " + stateName);
        this.atmState = atmStateFactory.getAtmState(stateName);
    }
    public void setCurCard(Card card){
        this.curCard = card;
    }

    public void addAccount(Account account) {
        accountMap.put(account.getAccountNo(), account);
    }

    public ATMInventory getAtmInventory() {
        return atmInventory;
    }

    public Card getCurCard() {
        return curCard;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public ATMState getAtmState() {
        return atmState;
    }
}