import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private static ParkingLot parkingLotInstance;

    private List<ParkingFloor> parkingFloors;

    private final Map<String, Ticket> activeTickets = new ConcurrentHashMap<>();

    private FeeStratergy feeStratergy;

    public static synchronized  ParkingLot getInstance(){
        if(parkingLotInstance == null){
            parkingLotInstance = new ParkingLot();

        }
        return parkingLotInstance;
    }

    private ParkingLot(){
        parkingFloors = new ArrayList<>();
        feeStratergy = new FlatFeeStratergy();
    }

    public void addFloor(ParkingFloor parkingFloor){
        this.parkingFloors.add(parkingFloor);
    }

    public synchronized Map<String, Ticket> getActiveTickets(){
        return activeTickets;
    }

    public synchronized Ticket parkVehicle(Vehicle vehicle,int at) throws Exception{
        try{
            for(ParkingFloor floor : parkingFloors){
                ParkingSpot parkingSpot = floor.getAvailableSpots(vehicle.vehicleType);
                if(parkingSpot!=null){
                    parkingSpot.doPark(vehicle);
                    Ticket ticket = new Ticket(UUID.randomUUID().toString(),at,-1,vehicle,parkingSpot);
                    return ticket;
                }
            }
        }catch (Exception e){
            System.out.println("No available spot for type = "+vehicle.getVehicleType());
            throw e;
        }
        return null;
    }

    public synchronized int unparkVehicle(Ticket ticket,int dt) throws Exception{
        if(ticket==null){
            throw new Exception("Invalid ticket");
        }
        ticket.leaveTime = dt;
        ParkingSpot parkingSpot = ticket.parkingSpot;
        parkingSpot.unpark();
        int price = feeStratergy.calculateFee(ticket);
        return price;
    }

}
