package observer;

public class UserPriceAlert implements PriceObserver {
    private final String userName;

    public UserPriceAlert(String userName) {
        this.userName = userName;
    }

    @Override
    public void update(long oldPrice, long newPrice) {
        long saved = oldPrice - newPrice;
        System.out.println("[Price alert for " + userName + "] Price dropped from "
                + oldPrice + " KZT to " + newPrice + " KZT (you save " + saved + " KZT)");
    }

    public String getUserName() {
        return userName;
    }
}
