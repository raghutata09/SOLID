import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        ParkingSpot parkingSpot  = new ParkingSpot(1, VehicleType.BUS);
        ParkingSpot parkingSpot2  = new ParkingSpot(2, VehicleType.CAR);
        List<ParkingSpot> parkingSpots = new ArrayList<>();
        parkingSpots.add(parkingSpot);
        parkingSpots.add(parkingSpot2);

        ParkingFloor f1 = new ParkingFloor(0,parkingSpots);


        ParkingLot parkingLot = ParkingLot.getInstance();
        parkingLot.addFloor(f1);
        Ticket ticket = null;
        Vehicle v1 = new Truck("123");
        try {
            System.out.println("Trying to park");
            ticket = parkingLot.parkVehicle(v1,1);
            if(ticket!=null){
                parkingLot.getActiveTickets().put(ticket.id,ticket);
                System.out.println("Got the ticket = "+ticket.id);
            }
        }catch (Exception e){
            System.out.println("Not able to park");
        }

        int price = parkingLot.unparkVehicle(ticket,3);
        System.out.println("Price for car is "+price);


    }
}