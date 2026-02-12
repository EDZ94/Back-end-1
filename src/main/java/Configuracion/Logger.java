package Configuracion;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    public static void info(String msg) {
        log("INFO", msg);
        System.out.println("[INFO]" + timestamp() + "-" + msg);
    }

    public static void error(String msg) {
        System.err.println("[ERROR]" + timestamp() + "-" + msg);
    }

    private static String timestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // ------- METODOS INFO WARNING ERROR: el log file es un txt de trazabilidad 
    
    private static final String LOG_FILE = "Log.txt";
    
    private static void log(String level, String msg) {
        String logEntry = String.format("[%s] %s - %s%n", level, timestamp(), msg); //Creamos objeto logentry
        try(FileWriter fw = new FileWriter(LOG_FILE, true)){
            fw.write(logEntry);
            
        }catch (IOException e){
                System.err.println("No se pudo escribir en el log" + e.getMessage());
                }
    }
    

    
    
    
}







