import java.io.FileWriter;

public class OrderService {

    private static final int BASE_A = 10;
    private static final int BASE_B = 20;

    private LoggerService logger;
    private Settings settings;

    public OrderService(LoggerService logger, Settings settings) {
        this.logger = logger;
        this.settings = settings;
    }

    public double calculateTotal(Order o) {

        int qty = o.urgent ? o.quantity + 1 : o.quantity;

        double total = qty * o.price + BASE_A + BASE_B;

        return applyUserAdjustment(total);
    }

    private double applyUserAdjustment(double total) {

        switch (settings.userType) {
            case REGULAR:
                return total - 5;
            case PREMIUM:
                return total + 5;
            case VIP:
            default:
                return total;
        }
    }

    public void saveOrderToFile(Order o, double total) {

        try (FileWriter fw = new FileWriter("order_data.txt", true)) {

            fw.write("U:" + o.userName + " P:" + o.productId + " T:" + total + "\n");

        } catch (Exception e) {
            System.out.println("Write error");
        }
    }

    public void printOrderReport(Order o, double total) {

        System.out.println("----- ORDER REPORT -----");
        System.out.println("User: " + o.userName);
        System.out.println("Product: " + o.productId);
        System.out.println("Quantity: " + o.quantity);
        System.out.println("Total: " + total);
        System.out.println("Date: " + o.date);
    }
}
