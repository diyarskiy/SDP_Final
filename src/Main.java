import decorator.Booking;
import facade.TravelBookingFacade;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TravelBookingFacade facade = new TravelBookingFacade();

        List<String> destinations = facade.getDestinations();
        System.out.println("Destinations:");
        for (int i = 0; i < destinations.size(); i++) {
            System.out.println((i + 1) + ". " + destinations.get(i));
        }
        int number = readNumber(input, "Choose a destination", 1, destinations.size());
        String destination = destinations.get(number - 1);

        int mode = readNumber(input, "Flight choice: 1 - cheapest, 2 - fastest", 1, 2);
        int nights = readNumber(input, "Hotel nights", 1, 30);
        int days = readNumber(input, "Rental days (0 for no car)", 0, nights);
        boolean insurance = readYesNo(input, "Add insurance?");
        boolean baggage = readYesNo(input, "Add extra baggage?");

        try {
            Booking booking = facade.bookTrip(destination, mode == 2, nights, days, insurance, baggage);
            System.out.println("\n" + booking.getDescription());                 
            System.out.println("Total price: " + booking.getTotalPrice() + " KZT");
        } catch (IllegalArgumentException e) {
            System.out.println("Booking failed: " + e.getMessage());
            return;
        }

        if (readYesNo(input, "\nSubscribe to price alerts?")) {
            System.out.print("Your name: ");
            facade.subscribeToPriceAlerts(input.next());
            int newPrice = readNumber(input, "Simulate a new price in KZT", 1, 100_000_000);
            facade.changePrice(newPrice);
        }
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

    private static boolean readYesNo(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String answer = input.next();
            if (answer.equalsIgnoreCase("y")) {
                return true;
            }
            if (answer.equalsIgnoreCase("n")) {
                return false;
            }
        }
    }
}
