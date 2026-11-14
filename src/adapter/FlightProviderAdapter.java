package adapter;

import java.util.ArrayList;
import java.util.List;
import model.Flight;

public class FlightProviderAdapter implements FlightProvider {
    private final LegacyFlightService service;

    public FlightProviderAdapter(LegacyFlightService service) {
        this.service = service;
    }

    @Override
    public List<Flight> getFlights() {
        List<Flight> flights = new ArrayList<>();
        for (String offer : service.loadOffers()) {
            String[] fields = offer.split(";");
            int price = Integer.parseInt(fields[4]);
            int minutes = Integer.parseInt(fields[5]);
            flights.add(new Flight(fields[0], fields[1], fields[2], fields[3], price, minutes));
        }
        return flights;
    }
}
