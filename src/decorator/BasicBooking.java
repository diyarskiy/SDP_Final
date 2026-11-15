package decorator;

import java.util.Objects;
import builder.Trip;

public class BasicBooking implements Booking {
    private final Trip trip;

    public BasicBooking(Trip trip) {
        this.trip = Objects.requireNonNull(trip, "Trip is required");
    }

    @Override
    public String getDescription() {
        return "Trip to " + trip.getRequest().getDestination();
    }

    @Override
    public long getTotalPrice() {
        return trip.getBasePrice();
    }

    public Trip getTrip() {
        return trip;
    }
}
