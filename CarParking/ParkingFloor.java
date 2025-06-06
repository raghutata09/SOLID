import java.util.List;
import java.util.Optional;

public class ParkingFloor {

    private final int floorNo;

    private final List<ParkingSpot> parkingSpots;

    public ParkingFloor(int floorNo, List<ParkingSpot> parkingSpots){
        this.floorNo=floorNo;
        this.parkingSpots=parkingSpots;
    }

    public synchronized ParkingSpot getAvailableSpots(VehicleType type){
        for(ParkingSpot parkingSpot : parkingSpots){
            if(!parkingSpot.isOccupied && parkingSpot.vehicleType==type){
                return parkingSpot;
            }
        }
        return null;
    }
}
