package facade;

import decorator.Booking;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private final List<Booking> bookings = new ArrayList<>();

    public void save(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getBookings() {
        return List.copyOf(bookings);
    }
}
