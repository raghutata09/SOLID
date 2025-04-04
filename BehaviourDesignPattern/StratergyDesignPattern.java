package BehaviourDesignPattern;

public class StratergyDesignPattern {

    /*
        Maanlo ek class h jisse 3 class extend kr rhe h example
        Vehicle ->SportsCar, PassengerCar, OffRoadCar
        Vehicle{
            drive("Normal Driviing")
            display("Normal Display")
        }

        SportsCar extends Vehicle{
            drive("Special Driviing")
            display("Special Display")
        }

        PassengerCar extends Vehicle{
            // No override so parent functions will come here
        }

        OffRoadCar extends Vehicle{
            drive("Special Driviing")
            display("Special Display")
        }

        Ab maanlo Offroadcar and SportsCar dono ka same hi drive style h..
        to code duplicate ho gya
        ===================
        Ab uppar case ko solve krna h to kya kr skte h

        DriveStratergy(){
        //Interface
        drive()
        }

        NormalDrive() implements DriveInterface{
        drive("Normal Drive")
        }

        SpecialDrive() implements DriveInterface {
        drive("Special Drive")
        }
        ----
        Vehicle{
            DriveStratergy ds;
            Vehicle(DriveStratergy ds){
                this.ds = ds;
            }
            drive(){
                ds.drive();
            }

        }
        SportsCar extends Vehicle{

            SportsCar(){
                super(new SpecialDrive());
            }

     */
}
