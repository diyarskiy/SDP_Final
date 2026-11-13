import java.util.Scanner;
import model.CarRental;
import model.Flight;
import model.Hotel;
import model.Trip;
import model.TripRequest;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Flight[] flights = {
            new Flight("TB101", "Air Astana", "Almaty", "Astana", 25000, 105),
            new Flight("TB205", "Fly Arystan", "Almaty", "Astana", 32000, 90)
        };
        Hotel hotel = new Hotel("City Inn", "Astana", 18000, 3);
        CarRental car = new CarRental("City Rent", "Kia Rio", 12000);

        System.out.println("Almaty -> Astana (sample offers)");
        for (int i = 0; i < flights.length; i++) {
            Flight flight = flights[i];
            System.out.println((i + 1) + ". " + flight.getId() + " " + flight.getAirline()
                    + ": " + flight.getPrice() + " KZT, " + flight.getDurationMinutes() + " min");
        }
        int number = readNumber(input, "Choose a flight", 1, flights.length);
        System.out.println(hotel.getName() + ": " + hotel.getPricePerNight() + " KZT per night");
        int nights = readNumber(input, "Hotel nights", 1, 30);
        System.out.println(car.getModel() + ": " + car.getPricePerDay() + " KZT per day");
        int days = readNumber(input, "Rental days (0 for no car)", 0, nights);
        TripRequest request = new TripRequest(hotel.getCity(), nights, days);
        Flight selected = flights[number - 1];
        Trip trip = new Trip(request, selected, hotel, days == 0 ? null : car);

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
