package BehaviourDesignPattern;


public class NullObjectPattern {
    /*
    jab bhi koi object aaye for eg Vehicle vehicle
    if(vehicle!=null){
        System.out.print(vehicle.getCapacity()
    }

    Aise objects bhut  hue to bhut baar hame null check lagana hoga... uppar vala shi h but
    tedious work h. Usee kam krne ke lie ham NullObjectPattern use krte h
     */

    public static void main(String[] args) {
        Vehicle vehicle = VehicleFactory.getVehicleObject("Bike");
        printVehicleDetails(vehicle);

    }

    public static void printVehicleDetails(Vehicle vehicle){
        System.out.println(vehicle.getTankCapacity());
    }
}

public class VehicleFactory{
    static Vehicle getVehicleObject(String typeOfVehicle){
        if("Car".equals(typeOfVehicle)){
            return new Car();
        }
        return new NullObject();//instead of sending null
    }
}

public interface Vehicle{
    int getTankCapacity();
}

public class Car implements Vehicle{

    @Override
    public int getTankCapacity(){
        return 40;
    }
}

public class NullObject implements Vehicle{
    //This will either do nothing or sends default values instead of sending null

    @Override
    public int getTankCapacity(){
        return 0;
    }
}