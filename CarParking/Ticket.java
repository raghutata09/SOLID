public class Ticket {

    String id;
    int arrivalTime;
    int leaveTime;
    Vehicle vehicle;
    ParkingSpot parkingSpot;

    Ticket(String id, int arrivalTime, int leaveTime, Vehicle vehicle, ParkingSpot parkingSpot){
        this.id= id;
        this.arrivalTime=arrivalTime;
        this.leaveTime=leaveTime;
        this.vehicle=vehicle;
        this.parkingSpot=parkingSpot;
    }
}
