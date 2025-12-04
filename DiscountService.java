public class DiscountService {

    public double applyDiscount(UserType type, double bill) {
        switch (type) {
            case REGULAR:
                return bill - 10;
            case PREMIUM:
                return bill - 2;
            case VIP:
                return bill - 1;
            default:
                return bill;
        }
    }
}
