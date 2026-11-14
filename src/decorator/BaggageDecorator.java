package decorator;

public class BaggageDecorator extends BookingDecorator {
    public static final long BAGGAGE_PRICE = 8000;

    public BaggageDecorator(Booking booking) {
        super(booking);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", extra baggage";
    }

    @Override
    public long getTotalPrice() {
        return super.getTotalPrice() + BAGGAGE_PRICE;
    }
}
