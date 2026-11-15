package adapter;

import java.util.List;

public class FlightRepository {
    private final List<Flight> flights;

    public FlightRepository(FlightProvider provider) {
        flights = List.copyOf(provider.getFlights());
    }

    public List<Flight> getFlights() {
        return flights;
    }
}
