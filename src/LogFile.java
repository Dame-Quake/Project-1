package src;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogFile {
    private String filePath ="logs\\logfile.txt" ;

    public LogFile(String filePath) {
        this.filePath = filePath;
    }

    public void logActivity(String message) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write("[" + timestamp + "] " + message + "\n");
        } catch (IOException e) {
            System.err.println("Logging failed: " + e.getMessage());
        }
    }
}

