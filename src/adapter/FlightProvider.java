package adapter;

import java.util.List;
import model.Flight;

public interface FlightProvider {
    List<Flight> getFlights();
}
