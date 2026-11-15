package builder;

import adapter.Flight;

public class Trip {
    private final TripRequest request;
    private final Flight flight;
    private final Hotel hotel;
    private final CarRental carRental;

    public Trip(TripRequest request, Flight flight, Hotel hotel, CarRental carRental) {
        if (request == null || flight == null || hotel == null) {
            throw new IllegalArgumentException("A request, flight and hotel are required");
        }
        if (!flight.getDestination().equalsIgnoreCase(request.getDestination())
                || !hotel.getCity().equalsIgnoreCase(request.getDestination())) {
            throw new IllegalArgumentException("Flight and hotel must match the destination");
        }
        if ((request.getRentalDays() > 0) != (carRental != null)) {
            throw new IllegalArgumentException("Choose a car only when renting for at least one day");
        }
        this.request = request;
        this.flight = flight;
        this.hotel = hotel;
        this.carRental = carRental;
    }

    public long getBasePrice() {
        long total = flight.getPrice() + (long) hotel.getPricePerNight() * request.getNights();
        if (carRental != null) {
            total += (long) carRental.getPricePerDay() * request.getRentalDays();
        }
        return total;
    }

    public TripRequest getRequest() {
        return request;
    }

    public Flight getFlight() {
        return flight;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public CarRental getCarRental() {
        return carRental;
    }
}
