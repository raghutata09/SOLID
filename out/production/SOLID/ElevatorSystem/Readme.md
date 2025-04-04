Building
    List<Elevator> liftLst;
    



Elevator
    List<Floor> floorsLst
    Display
    Direction
    Current Floor
    Buttons

ExternalButton
    -UP
    -DOWN

Floor
------------------------

Elevator Car
    -id
    -Display disp
    -int currentFloor
    -Direction dir
    -Status status
    -InternalButton obj

EnumDirection
    UP, DOWN

EnumStatus
    IDLE,MOVING

InternalButton
    InternalButtonDispatcher ibd;
    -
    pressButton(int button)

InternalButtonDispatch
    List<ElevatorController>
    ----
    submitReq(int liftId)
ExternalButtonDispatch
    List<ElevatorController>
    submitReq()
OddEvenDispatch implements ExternalButtonDispatch
    oddEvenLogic
FixedFloorDispatch implements ExternalButtonDispatch
    fixedFloorDispatch



ElevatorController
    ElevatorObj
    MinHeap
    MaxHeap
    PriorityQueue
    ----
    acceptNewReq(int floor,Direction dir)
    controlElevatorController()