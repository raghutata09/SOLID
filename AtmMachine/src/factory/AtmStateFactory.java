package factory;

import states.*;

public class AtmStateFactory {

    public ATMState getAtmState(String state){
        if(state=="idle"){
            return new IdleState();
        }
        else if(state=="card"){
            return new HasCardState();
        }
        else if(state=="selection"){
            return new SelectOperationState();
        }
        else if(state=="transaction"){
            return new TransactionState();
        }
        else {
            return null;
        }
    }
}
