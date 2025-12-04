import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        SettingsLoader loader = new SettingsLoader();
        Settings settings = loader.load();

        LoggerService logger = new LoggerService();
        OrderService orderService = new OrderService(logger, settings);
        DiscountService discountService = new DiscountService();
        ShippingCalculator shipping = new ShippingCalculator();
        NotificationService notify = new NotificationService();

        Order order = new Order();
        order.userName = "Ali";
        order.productId = 101;
        order.quantity = 2;
        order.price = 999.99;
        order.address = "Karachi";
        order.urgent = true;
        order.notes = "Handle carefully";
        order.date = LocalDate.of(2025, 11, 12);

        logger.add("Start:" + order.userName);

        double total = orderService.calculateTotal(order);
        orderService.saveOrderToFile(order, total);
        orderService.printOrderReport(order, total);

        System.out.println("Discounted Total: " +
                discountService.applyDiscount(settings.userType, 5000));

        System.out.println("Shipping Cost: " + shipping.calculate(35));

        logger.exportToFile();

        notify.notifyUser("Ali", "Your order has been processed successfully!");

        logger.add("End:" + order.userName);
    }
}
