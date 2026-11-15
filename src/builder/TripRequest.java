package builder;

public class TripRequest {
    private final String destination;
    private final int nights;
    private final int rentalDays;

    public TripRequest(String destination, int nights, int rentalDays) {
        if (nights < 1 || rentalDays < 0) {
            throw new IllegalArgumentException("Check nights and rental days");
        }
        this.destination = destination;
        this.nights = nights;
        this.rentalDays = rentalDays;
    }

    public String getDestination() {
        return destination;
    }

    public int getNights() {
        return nights;
    }

    public int getRentalDays() {
        return rentalDays;
    }
}
