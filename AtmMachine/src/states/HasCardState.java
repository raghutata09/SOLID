package states;

import entities.AtmMachine;
import entities.Card;

public class HasCardState extends ATMState {

    @Override
    public void authenticatePin(AtmMachine atm, int pin) {
        Card card = atm.getCurCard();
        if (card == null) {
            System.out.println("No card inserted.");
            atm.setAtmState("idle");
            return;
        }

        if (card.getPin() == pin) {
            System.out.println("PIN is correct.");
            atm.setAtmState("selection"); // ✅ move to selection state
        }else {
            System.out.println("Incorrect PIN.");
            atm.setAtmState("idle");
        }
    }
}