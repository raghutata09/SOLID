package states;

import entities.AtmMachine;
import entities.Card;
import enums.TransactionType;

public class IdleState extends ATMState {
    @Override
    public void insertCard(AtmMachine atm, Card card) {
        if (atm.insertCard(card)) {
            System.out.println("Card inserted. Please enter PIN.");
            atm.setAtmState("card");
        }
    }

    @Override
    public void selectOperation(AtmMachine atm, TransactionType transactionType) {
        System.out.println("Insert card first before selecting operation.");
    }

}