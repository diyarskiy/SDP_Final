package model;

public class Flight {
    private final String id;
    private final String airline;
    private final String departureCity;
    private final String destination;
    private final int price;
    private final int durationMinutes;

    public Flight(String id, String airline, String departureCity, String destination, int price, int durationMinutes) {
        if (price < 0 || durationMinutes < 1) {
            throw new IllegalArgumentException("Invalid flight price or duration");
        }
        this.id = id;
        this.airline = airline;
        this.departureCity = departureCity;
        this.destination = destination;
        this.price = price;
        this.durationMinutes = durationMinutes;
    }

    public String getId() {
        return id;
    }

    public String getAirline() {
        return airline;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getDestination() {
        return destination;
    }

    public int getPrice() {
        return price;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }
}
