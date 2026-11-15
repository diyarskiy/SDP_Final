package strategy;

import java.util.List;
import java.util.Objects;
import adapter.Flight;

public class OfferSelector {
    private OfferStrategy strategy;

    public OfferSelector(OfferStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "Strategy is required");
    }

    public void setStrategy(OfferStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "Strategy is required");
    }

    public Flight selectFlight(List<Flight> flights) {
        return strategy.selectFlight(flights);
    }
}
