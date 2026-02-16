package DbManager;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final String LOG_FILE = "log.txt";
    
    // METODOS INFO WARNIGN ERROR
    public static void info(String msg) {
        log("INFO", msg);
    }

    public static void error(String msg) {
        System.err.println("[ERROR] " + timestamp() + " - " + msg);
    }

    private static String timestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private static void log(String level, String msg) {
        String logEntry = String.format("[%s] %s - %s%n", level, timestamp(), msg); //Creamos el objeto logEntry
        try (FileWriter fw = new FileWriter(LOG_FILE, true)) {
            fw.write(logEntry);
        } catch (IOException e) {
            System.err.println("No se puedo escribir en el log" + e.getMessage());
        }
    }

}
