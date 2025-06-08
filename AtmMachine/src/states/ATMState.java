package states;

import entities.AtmMachine;
import entities.Card;
import enums.TransactionType;


public abstract class ATMState {

    public void insertCard(AtmMachine atm, Card card) {
        throw new UnsupportedOperationException("Operation not supported in current state.");
    }

    public void authenticatePin(AtmMachine atm, int pin) {
        throw new UnsupportedOperationException("Operation not supported in current state.");
    }

    public void selectOperation(AtmMachine atm, TransactionType transactionType) {
        throw new UnsupportedOperationException("Operation not supported in current state.");
    }

    public void performCashWithdrawal(AtmMachine atm, int amount) {
        throw new UnsupportedOperationException("Operation not supported in current state.");
    }

    public void returnCard(AtmMachine atm) {
        throw new UnsupportedOperationException("Operation not supported in current state.");
    }

    public void exit() {
        System.out.println("OOPS!! Something went wrong");
    }
}