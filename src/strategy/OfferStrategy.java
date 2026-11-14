package strategy;

import java.util.List;
import model.Flight;

public interface OfferStrategy {
    Flight selectFlight(List<Flight> flights);
}
