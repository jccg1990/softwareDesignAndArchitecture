package week2;

import java.util.ArrayList;
import java.util.List;

public class Channel implements Subject {

    private List<Observer> observers = new ArrayList<>();
    private String channelName;
    private String status;

    public Channel(String channelName, String status) {
        this.channelName = channelName;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observers);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this.status);
        }
    }
}
