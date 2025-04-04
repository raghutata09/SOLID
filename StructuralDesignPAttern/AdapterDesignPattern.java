package StructuralDesignPAttern;

public class AdapterDesignPattern {
    /*
        It creates bridge between expected Interface to existing Interface

        Eg
        AdapterInterface-> ConcreteAdapter
     */

    // -------------- Client
    public static void main(String[] args) {
        WightMachineAdapter wightMachineAdapter = new WightMachineAdapterImpl(new WightMachineForBabies());

        System.out.println(wightMachineAdapter.getWeightInKg());
    }


}

// ---------------------    Adaptee

public interface WightMachine{
    public double getWeightInPounds();
}

public class WightMachineForBabies implements WightMachine{

    @Override
    public double getWeightInPounds(){
        return 28;
    }
}

// ------------------ Adapter

public interface WightMachineAdapter{
    public double getWeightInKg();
}

public class WightMachineAdapterImpl implements WightMachineAdapter{
    WightMachine wightMachine;
    public WightMachineAdapterImpl(WightMachine wightMachine){this.wightMachine=wightMachine;}

    @Override
    public double getWeightInPounds(){
        double wtInPound = wightMachine.getWeightInPounds();
        double wtInKg= wtInPound*.45;
        return wtInKg;
    }
}

