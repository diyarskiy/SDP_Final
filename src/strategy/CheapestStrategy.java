package strategy;

import java.util.Comparator;
import java.util.List;
import model.Flight;

public class CheapestStrategy implements OfferStrategy {
    @Override
    public Flight selectFlight(List<Flight> flights) {
        if (flights == null || flights.isEmpty()) {
            throw new IllegalArgumentException("Flight list must not be empty");
        }
        return flights.stream()
                .min(Comparator.comparingInt(Flight::getPrice)
                        .thenComparingInt(Flight::getDurationMinutes))
                .orElseThrow();
    }
}
