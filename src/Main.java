import adapter.FlightProviderAdapter;
import adapter.FlightRepository;
import adapter.LegacyFlightService;
import builder.TripBuilder;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.CarRental;
import model.Flight;
import model.Hotel;
import model.Trip;
import model.TripRequest;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        FlightProviderAdapter provider = new FlightProviderAdapter(new LegacyFlightService());
        FlightRepository repository = new FlightRepository(provider);
        List<Flight> flights = repository.getFlights();
        Hotel hotel_ast = new Hotel("Ritz Carlton", "Astana", 18000, 3);
        Hotel hotel_ala = new Hotel("Raddison Blue", "Almaty", 25000, 4);
        CarRental car_ast = new CarRental("City Rent", "Kia Rio", 12000);
        CarRental car_ala = new CarRental("Yandex", "Chevrolet Cobalt", 12500);

        Map<String, Hotel> hotels = Map.of(hotel_ast.getCity(), hotel_ast, hotel_ala.getCity(), hotel_ala);
        Map<String, CarRental> rentals = Map.of("Astana", car_ast, "Almaty", car_ala);

        System.out.println("Available flights:");
        for (int i = 0; i < flights.size(); i++) {
            Flight flight = flights.get(i);
            System.out.println((i + 1) + ". " + flight.getId() + " " + flight.getAirline()
                    + " (" + flight.getDepartureCity() + " -> " + flight.getDestination() + "): "
                    + flight.getPrice() + " KZT, " + flight.getDurationMinutes() + " min");
        }
        int number = readNumber(input, "Choose a flight", 1, flights.size());
        Flight selected = flights.get(number - 1);
        String destination = selected.getDestination();
        Hotel hotel = hotels.get(destination);
        CarRental car = rentals.get(destination);
        if (hotel == null) {
            System.out.println("No hotel available in " + destination + ".");
            return;
        }
        System.out.println(hotel.getName() + ": " + hotel.getPricePerNight() + " KZT per night");
        int nights = readNumber(input, "Hotel nights", 1, 30);
        int days = 0;
        if (car != null) {
            System.out.println(car.getCompany() + " - " + car.getModel() + ": " + car.getPricePerDay() + " KZT per day");
            days = readNumber(input, "Rental days (0 for no car)", 0, nights);
        } else {
            System.out.println("No car rental available in " + destination + ".");
        }
        TripRequest request = new TripRequest(destination, nights, days);
        TripBuilder builder = new TripBuilder(request).setFlight(selected).setHotel(hotel);
        if (days > 0) {
            builder.setCarRental(car);
        }
        Trip trip = builder.build();

        System.out.println("\nTrip to " + request.getDestination());
        System.out.println("Flight: " + selected.getId() + ", " + selected.getPrice() + " KZT");
        System.out.println("Hotel: " + hotel.getName() + ", " + nights + " nights");
        if (trip.getCarRental() != null) {
            System.out.println("Car: " + car.getModel() + ", " + days + " days");
        }
        System.out.println("Base price: " + trip.getBasePrice() + " KZT");
    }

    private static int readNumber(Scanner input, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt + " [" + min + "-" + max + "]: ");
            if (input.hasNextInt()) {
                int value = input.nextInt();
                if (value >= min && value <= max) {
                    return value;
                }
            } else {
                input.next();
            }
            System.out.println("Enter a number from " + min + " to " + max + ".");
        }
    }
}
