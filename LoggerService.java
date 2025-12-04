import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class LoggerService {

    private List<String> logs = new ArrayList<>();

    public void add(String s) {
        logs.add(s);
    }

    public void exportToFile() {
        try (FileWriter fw = new FileWriter("logs.txt")) {
            for (String log : logs) {
                fw.write(log + "\n");
            }
        } catch (Exception e) {
            System.out.println("Log export error");
        }
    }
}
