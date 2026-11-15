package observer;

public interface PriceObserver {
    void update(long oldPrice, long newPrice);
}
