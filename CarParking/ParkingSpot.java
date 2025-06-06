public class ParkingSpot {
    private int spotNo;
    VehicleType vehicleType;
    boolean isOccupied;
    Vehicle vehicle;

    ParkingSpot(int spotNo, VehicleType vehicleType){
        this.spotNo = spotNo;
        this.vehicleType=vehicleType;
        isOccupied=false;
    }

    public synchronized boolean doPark(Vehicle vehicle){
        if(isOccupied || vehicle.getVehicleType()!=vehicleType){
            return false;
        }

        this.vehicle = vehicle;
        isOccupied=true;
        return true;
    }

    public synchronized void unpark(){
        this.vehicle=null;
        this.isOccupied=false;
        return;
    }



}
