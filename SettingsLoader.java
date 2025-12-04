import java.io.File;
import java.util.Scanner;

public class SettingsLoader {

    public Settings load() {

        Settings s = new Settings();

        try (Scanner sc = new Scanner(new File("settings.conf"))) {

            while (sc.hasNextLine()) {

                String line = sc.nextLine();

                if (line.startsWith("U")) {
                    int type = Integer.parseInt(line.substring(2));
                    s.userType = (type == 1 ? UserType.REGULAR :
                                   type == 2 ? UserType.PREMIUM :
                                               UserType.VIP);
                }

                if (line.startsWith("P")) {
                    String val = line.substring(2);
                    s.paymentType = PaymentType.valueOf(val);
                }
            }

        } catch (Exception e) {
            System.out.println("Settings not loaded");
        }

        return s;
    }
}
