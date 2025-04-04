package BehaviourDesignPattern;

import javax.management.Notification;
import java.util.ArrayList;
import java.util.List;

public class ObserverDesignPattern {

    /*
    There are 2 things involved
    1. Observable     2. Observer

    JAb bhi Observable ka koi status update hoga, vo notify kroge sare observers ko
    ----------
    ObservableInterface.java

    List<ObserverInterface> ob;
    add(ObserverInterface ob);
    remove(ObserverInterface ob)
    notify()
    setDAta()
    getData()
     ----------
     ObserverInterface.java

     update()



     ----
     Lets understand with OutOf sTock exmaple for Amazon and we have to notify
     once it's in stock

     */

    public static void main(String[] args) {
        StocksObservable iphone = new IphoneStocksObservable();
        NotificationAlertObserver o1 = new EmailAlertObserverImpl("abc@gmai.com",iphone);
        NotificationAlertObserver o2 = new EmailAlertObserverImpl("abc@gmai.com",iphone);
        NotificationAlertObserver o3 = new EmailAlertObserverImpl("abc@gmai.com",iphone);

        iphone.add(o1);
        iphone.add(o2);
        iphone.add(o3);
        iphone.setStockCount(10);
    }
}
public class EmailAlertObserverImpl implements NotificationAlertObserver{
    String emailId;
    StocksObservable so;

    public EmailAlertObserverImpl(String emailId, StocksObservable so){
        this.emailId = emailId;
        this.so=so;
    }

    @Override
    public void update(){
        sendEmail(emailId,"product is in stock. Please hurry");
    }

    private void sendEmail(String emailId, String msg){
        System.out.println("Mail sent  to "+emailId);
    }
}

public interface StocksObservable{
    public void add(NotificationAlertObserver observer);
    public void remove(NotificationAlertObserver observer);

    public void notifySubscribers();
    public void setStockCount(int newStock);
    public int getStockCount();
}

public interface NotificationAlertObserver{
    public void update();
}

public class IphoneStocksObservable implements StocksObservable{
    public List<NotificationAlertObserver> observerList = new ArrayList<>();

    public int stockCount = 0;
    public void add(NotificationAlertObserver observer){
        observerList.add(observer);
    }
    public void remove(NotificationAlertObserver observer){
        observerList.remove(observer);
    }

    public void notifySubscribers(){
        for(NotificationAlertObserver observer: observerList){
            observer.update();
        }
    }
    public void setStockCount(int newStock){
        if(stockCount==0){
            notifySubscribers();
        }
        stockCount +=newStock;

    }
    public int getStockCount(){
        return stockCount;
    }
}
