Vehicle
    -VehcleNo
    -VehcleType
VehcleType
    -2 wheeler
    -4 wheeler
    


SPOT(ENUM)
    -SMALL
    -MEDIUM
    -LARGE

PARKING SPOT
    -FloorNo
    -id
    -isEmpty
    -Price
    -Vehicle v
    -parkVehcle(Vehicle v)  
        this.v =v
        isEmpty=false
    -RemoveVehicle()
        this.v=null
        isEmpty=True

TwoWheelerSpot implements PARKINGSPOT
    Price(return 10;)
FourWheelerSpot implements PARKINGSPOT
    Price(return 20)
    
//In future if we have heavywheicleCategory, we can simply add that
    


ParkingSpotMAnager
    List<ParkingSpot> psLst;
    ParkingSpotMAnager(List ps){
        this.psLst = ps;}
    
    findParkingSpace()
    addParkingSpace()
    removePArkingSpace()
    parkVehicle()
    RemoveVehicle()

TwoWheelManager
    List<Ps> lst2 = [[0,0,0,],[0,0,0]]//Eg 300 spots
    TwoWheelManager(){
        super(lst2)
    }
    //List of list isiliye taki aage kabhi floors add ho
    // to no of floors ke accorindg parking spots ho jaynge, and agar ek hi floor h toh lst2= [0,0,0,0] aise bhi de skte the
FourWheelManager
    List<Ps> lst4 = [[0,0,0,],[0,0,0]]//Eg 600 spots
    TwoWheelManager(){
    super(lst4)
    }

ParkingSpotFactory
    if(vehicletype==TwoWheeller){
        return new TwoWheelManager();
    }
    else{
        return new FourWheelManager();
    }

ParkingStratergy


NearToEntranceStratergy extends ParkingStratergy
    find()

NearToEntranceElevatorStratergy extends ParkingStratergy
    find()

DefaultStratergy
    find()

TICKET
    -VEHCILE v 
    -ENTRY_TIME
    -ParkingSPot
    
FLOOR
    -MAP<SpotType,Integer> mp;

BUILDING
    -List<Integer> floors;
    -List<Integer> entryGate;
    -List<Integer> exitGate;

GATETYPE
ENTRY
EXIT

Entrance
    -PsFactory factory;
    -PsManager manager;
    -checkParkingSpace(vehicleType)
    -updateParkingSpot(vehicle)
    -generateTicket()
Exit
    -CostComputation cc;
    -updateParkingSpot(carType)
    -updateMoney(cc.price(ticket));

CostComputation
    PriceStratergy ps;
CostComputation(PriceStratergy ps){
        this.ps=ps}
    calculateCost(
        this.ps.price();
    )
    

2wheelerCc implements CostComputation
    2wheelerCc(){
        super(hourPs);}
    
4 wheelerCc implements CostComputation
    4wheelerCc(){
    super(minutePs);}


PriceStratergy (i.e. hourl base or default)
    //Default method
    price(return 20;)

HourlyStratergy
    price(Ticket){
        noOfHours*20;
    }

DefaultStratergy
    price(Ticket){
    noOfHours*10;
    }