package strategy;

import java.util.List;
import adapter.Flight;

public interface OfferStrategy {
    Flight selectFlight(List<Flight> flights);
}
