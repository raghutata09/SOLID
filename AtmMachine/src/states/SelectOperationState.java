package states;

import entities.AtmMachine;
import enums.TransactionType;


public class SelectOperationState extends ATMState {
    @Override
    public void selectOperation(AtmMachine atm, TransactionType transactionType) {
        atm.setTransactionType(transactionType);
        System.out.println("Transaction selected: " + transactionType);
        atm.setAtmState("transaction");
    }
}