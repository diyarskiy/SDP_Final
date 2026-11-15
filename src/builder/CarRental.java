package builder;

public class CarRental {
    private final String company;
    private final String model;
    private final int pricePerDay;

    public CarRental(String company, String model, int pricePerDay) {
        if (pricePerDay < 0) {
            throw new IllegalArgumentException("Invalid rental price");
        }
        this.company = company;
        this.model = model;
        this.pricePerDay = pricePerDay;
    }

    public String getCompany() {
        return company;
    }

    public String getModel() {
        return model;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }
}
