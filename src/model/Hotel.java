package model;

public class Hotel {
    private final String name;
    private final String city;
    private final int pricePerNight;
    private final int rating;

    public Hotel(String name, String city, int pricePerNight, int rating) {
        if (pricePerNight < 0 || rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Invalid hotel price or rating");
        }
        this.name = name;
        this.city = city;
        this.pricePerNight = pricePerNight;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public int getRating() {
        return rating;
    }
}
