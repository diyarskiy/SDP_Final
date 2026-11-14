package decorator;

import java.util.Objects;

public abstract class BookingDecorator implements Booking {
    protected final Booking booking;

    protected BookingDecorator(Booking booking) {
        this.booking = Objects.requireNonNull(booking, "Booking is required");
    }

    @Override
    public String getDescription() {
        return booking.getDescription();
    }

    @Override
    public long getTotalPrice() {
        return booking.getTotalPrice();
    }
}
