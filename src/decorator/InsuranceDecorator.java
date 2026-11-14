package decorator;

public class InsuranceDecorator extends BookingDecorator {
    public static final long INSURANCE_PRICE = 5000;

    public InsuranceDecorator(Booking booking) {
        super(booking);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", travel insurance";
    }

    @Override
    public long getTotalPrice() {
        return super.getTotalPrice() + INSURANCE_PRICE;
    }
}
