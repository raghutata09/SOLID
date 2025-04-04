package BehaviourDesignPattern;

public class ChainOfResponsibility {
    /*
    Example for above
        -ATM/Vending Machine
        -Design Logger


        jab bhi client ek req bhejege, and usse tension na ho ki konsa reciever usse use krega tab use krte h isse
        Sender -> Reciever1 -> Reciever 2 -> Reciever3 -> Sends back response

        Eg-
        customer ko 2600 rs nikalne h
        Customer-> ( 2000rs handler , 500rs handler, 100rs handler)

        iss case m 2000 rs pehle niklega fir aage 600 rs jaynge.. agar enough notes h toh 500rs ka
        1 note niklega and aage jayga fir 100 ka enough note h to response bhejga , nit to vapas aajayga

        Logger ke 3 types maanlo-> INFO, DEBUG, ERROR
        Logger log = new Logger()
        log.info("INFO","msg1");
        log.info("DEBUG","msg2");
        log.info("ERROR","msg3");
     */

    public static void main(String args[]) {

        LogProcessor logObject = new InfoLogProcessor(new DebugLogProcessor(new ErrorLogProcessor(null)));

        logObject.log(LogProcessor.ERROR, "exception happens");
        logObject.log(LogProcessor.DEBUG, "need to debug this ");
        logObject.log(LogProcessor.INFO, "just for info ");

    }

}

public abstract class LogProcessor{
    public static int INFO=1;
    public static int DEBUG=2;
    public static int ERROR=3;
    LogProcessor nextLogProcessor;
    LogProcessor(LogProcessor logProcessor){
        this.nextLogProcessor= logProcessor;
    }

    public void log(int logLevel,String message){
        if(nextLogProcessor!=null){
            nextLogProcessor.log(logLevel,message);
        }
    }
}

public class InfoLogProcessor extends LogProcessor{

    InfoLogProcessor(LogProcessor nexLogProcessor){
        super(nexLogProcessor);
    }

    public void log(int logLevel,String message){

        if(logLevel == INFO) {
            System.out.println("INFO: " + message);
        } else{

            super.log(logLevel, message);
        }

    }
}
public class DebugLogProcessor extends LogProcessor{

    DebugLogProcessor(LogProcessor nexLogProcessor){
        super(nexLogProcessor);
    }

    public void log(int logLevel,String message){

        if(logLevel == DEBUG) {
            System.out.println("DEBUG: " + message);
        } else{

            super.log(logLevel, message);
        }

    }
}
public class ErrorLogProcessor extends LogProcessor{

    ErrorLogProcessor(LogProcessor nexLogProcessor){
        super(nexLogProcessor);
    }

    public void log(int logLevel,String message){

        if(logLevel == ERROR) {
            System.out.println("ERROR: " + message);
        } else{

            super.log(logLevel, message);
        }

    }
}





