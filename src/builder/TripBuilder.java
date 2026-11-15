package builder;

import adapter.Flight;

public class TripBuilder {
    private final TripRequest request;
    private Flight flight;
    private Hotel hotel;
    private CarRental car;

    public TripBuilder(TripRequest request) {
        this.request = request;
    }

    public TripBuilder setFlight(Flight flight) {
        this.flight = flight;
        return this;
    }

    public TripBuilder setHotel(Hotel hotel) {
        this.hotel = hotel;
        return this;
    }

    public TripBuilder setCarRental(CarRental car) {
        this.car = car;
        return this;
    }

    public Trip build() {
        return new Trip(request, flight, hotel, car);
    }
}
