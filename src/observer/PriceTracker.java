package observer;

import java.util.ArrayList;
import java.util.List;

public class PriceTracker {
    private final List<PriceObserver> observers = new ArrayList<>();
    private long currentPrice;

    public PriceTracker(long initialPrice) {
        if (initialPrice < 0) {
            throw new IllegalArgumentException("Invalid price");
        }
        this.currentPrice = initialPrice;
    }

    public void subscribe(PriceObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(PriceObserver observer) {
        observers.remove(observer);
    }

    public void setPrice(long newPrice) {
        if (newPrice < 0) {
            throw new IllegalArgumentException("Invalid price");
        }
        long oldPrice = currentPrice;
        currentPrice = newPrice;
        if (newPrice < oldPrice) {
            notifyObservers(oldPrice, newPrice);
        }
    }

    public long getCurrentPrice() {
        return currentPrice;
    }

    private void notifyObservers(long oldPrice, long newPrice) {
        for (PriceObserver observer : observers) {
            observer.update(oldPrice, newPrice);
        }
    }
}
