package facade;

import adapter.FlightProviderAdapter;
import adapter.FlightRepository;
import adapter.LegacyFlightService;
import builder.TripBuilder;
import decorator.BaggageDecorator;
import decorator.BasicBooking;
import decorator.Booking;
import decorator.InsuranceDecorator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import builder.CarRental;
import adapter.Flight;
import builder.Hotel;
import builder.Trip;
import builder.TripRequest;
import observer.PriceTracker;
import observer.UserPriceAlert;
import strategy.CheapestStrategy;
import strategy.FastestStrategy;
import strategy.OfferSelector;

public class TravelBookingFacade {
    private final FlightRepository flightRepository;
    private final OfferSelector offerSelector;
    private final BookingService bookingService = new BookingService();
    private final Map<String, Hotel> hotels;
    private final Map<String, CarRental> rentals;
    private final Map<String, UserPriceAlert> alerts = new HashMap<>();
    private PriceTracker priceTracker;

    public TravelBookingFacade() {
        flightRepository = new FlightRepository(new FlightProviderAdapter(new LegacyFlightService()));
        offerSelector = new OfferSelector(new CheapestStrategy());
        Hotel hotel_ast = new Hotel("Ritz Carlton", "Astana", 18000, 3);
        Hotel hotel_ala = new Hotel("Raddison Blue", "Almaty", 25000, 4);
        CarRental car_ast = new CarRental("City Rent", "Kia Rio", 12000);
        CarRental car_ala = new CarRental("Yandex", "Chevrolet Cobalt", 12500);
        hotels = Map.of(hotel_ast.getCity(), hotel_ast, hotel_ala.getCity(), hotel_ala);
        rentals = Map.of("Astana", car_ast, "Almaty", car_ala);
    }

    public List<String> getDestinations() {
        List<String> destinations = new ArrayList<>();
        for (Flight flight : flightRepository.getFlights()) {
            if (!destinations.contains(flight.getDestination())) {
                destinations.add(flight.getDestination());
            }
        }
        return destinations;
    }

    public Booking bookTrip(String destination, boolean fastest, int nights, int rentalDays,
                            boolean insurance, boolean baggage) {
        Flight flight = selectFlight(destination, fastest);
        Trip trip = buildTrip(flight, nights, rentalDays);
        Booking booking = addExtras(new BasicBooking(trip), insurance, baggage);
        bookingService.save(booking);
        startPriceTracking(booking);
        return booking;
    }

    public void subscribeToPriceAlerts(String userName) {
        requirePriceTracker();
        if (alerts.containsKey(userName)) {
            return;
        }
        UserPriceAlert alert = new UserPriceAlert(userName);
        alerts.put(userName, alert);
        priceTracker.subscribe(alert);
    }

    public void unsubscribeFromPriceAlerts(String userName) {
        requirePriceTracker();
        UserPriceAlert alert = alerts.remove(userName);
        if (alert != null) {
            priceTracker.unsubscribe(alert);
        }
    }

    public void changePrice(long newPrice) {
        requirePriceTracker();
        priceTracker.setPrice(newPrice);
    }

    public List<Booking> getSavedBookings() {
        return bookingService.getBookings();
    }

    private Flight selectFlight(String destination, boolean fastest) {
        offerSelector.setStrategy(fastest ? new FastestStrategy() : new CheapestStrategy());
        return offerSelector.selectFlight(flightsTo(destination));
    }

    private Trip buildTrip(Flight flight, int nights, int rentalDays) {
        String city = flight.getDestination();
        Hotel hotel = hotels.get(city);
        if (hotel == null) {
            throw new IllegalArgumentException("No hotel available in " + city);
        }
        TripBuilder builder = new TripBuilder(new TripRequest(city, nights, rentalDays))
                .setFlight(flight)
                .setHotel(hotel);
        if (rentalDays > 0) {
            builder.setCarRental(rentals.get(city));
        }
        return builder.build();
    }

    private Booking addExtras(Booking booking, boolean insurance, boolean baggage) {
        if (insurance) {
            booking = new InsuranceDecorator(booking);
        }
        if (baggage) {
            booking = new BaggageDecorator(booking);
        }
        return booking;
    }

    private void startPriceTracking(Booking booking) {
        priceTracker = new PriceTracker(booking.getTotalPrice());
        alerts.clear();
    }

    private List<Flight> flightsTo(String destination) {
        List<Flight> result = new ArrayList<>();
        for (Flight flight : flightRepository.getFlights()) {
            if (flight.getDestination().equalsIgnoreCase(destination)) {
                result.add(flight);
            }
        }
        if (result.isEmpty()) {
            throw new IllegalArgumentException("No flights to " + destination);
        }
        return result;
    }

    private void requirePriceTracker() {
        if (priceTracker == null) {
            throw new IllegalStateException("Book a trip first");
        }
    }
}
