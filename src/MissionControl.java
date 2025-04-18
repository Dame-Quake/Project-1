package src;
import java.util.*;
import java.io.*;
/**
 * @author Jorge Chavarria Damian Villarreal 
 * @version 1.0
 * @since 2025-04-9
 * The {@code MissionControl} class manages the coordination of space object tracking,
 * user sessions, and system logging. It acts as the central controller for the application,
 * linking together users, tracking systems, and data operations.
 */
public class MissionControl {
    
    /** List of space objects being tracked.*/
    private List<SpaceObject> objects = new ArrayList<>();
    /** The current user of the system.*/
    private User currentUser;
    /** The path to the log file.*/
    private String logFilePath = "log.txt";
    /** The tracking system used for tracking space objects.*/
    private TrackingSystem trackingSystem;

    /**
     * Constructs a new {@code MissionControl} instance with the provided tracking system.
     *
     * @param trackingSystem the tracking system to be used by this controller
     */

    public MissionControl(TrackingSystem trackingSystem){
        this.trackingSystem = trackingSystem;
    }

    /**Sets the user currently interacting with the system */
    public void setCurrentUser(User user){
        this.currentUser = user;
    }

    /**Initiates object tracking based on their types using the tracking system */
    public void performTracking(){
        trackingSystem.trackByType("DEBRIS");
    }
    /**Loads external or persisted data into the mission control system*/
    public void loadData(){
        String path = "rso_metrics.csv";
        String line;
    
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String headerLine = br.readLine(); // skip headers
    
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",", -1);
    
                String recordID = tokens[0];
                String satelliteName = tokens[2];
                String country = tokens[3];
                String orbitType = tokens[4];
                String objectType = tokens[5];
                int launchYear = parseInt(tokens[6]);
                String launchSite = tokens[7];
                double longitude = parseDouble(tokens[8]);
                double avgLongitude = parseDouble(tokens[9]);
                String geohash = tokens[10];
                int daysOld = parseInt(tokens[18]);
                int conjunctionCount = parseInt(tokens[19]);

                SpaceObject obj = switch (objectType.toUpperCase()) {
                    case "DEBRIS" -> new Debris();
                    case "PAYLOAD" -> new Payload();
                    case "ROCKET BODY" -> new Satellite();
                    case "UNKNOWN" -> new Unknown();
                    default -> null;
                };

                if (obj != null) {
                    obj.recordID = recordID;
                    obj.name = satelliteName;
                    obj.country = country;
                    obj.orbitType = orbitType;
                    obj.launchYear = launchYear;
                    obj.launchSite = launchSite;
                    obj.longitude = longitude;
                    obj.avgLongitude = avgLongitude;
                    obj.geohash = geohash;
                    obj.daysOld = daysOld;
                    obj.conjunctionCount = conjunctionCount;

                    objects.add(obj);
                }
            }

            System.out.println("Data loaded. Objects count: " + objects.size());
            trackingSystem.setObjects(objects); // pass to tracking system

        } catch (IOException e) {
            System.out.println("Error loading CSV: " + e.getMessage());
        }
    }

    /**Tracks all registered space objects in the system */
    public void trackObjects(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nSelect object type to track:");
        System.out.println("1. Debris\n2. Payload\n3. Rocket Body\n4. Unknown\n5. Back");
        System.out.print("Choice: ");
        String type = switch (sc.nextLine().trim()) {
            case "1" -> "DEBRIS";
            case "2" -> "PAYLOAD";
            case "3" -> "ROCKET BODY";
            case "4" -> "UNKNOWN";
            default -> null;
        };

        if (type == null) return;

        List<SpaceObject> filtered = trackingSystem.trackByType(type);
        for (SpaceObject obj : filtered) {
            System.out.println(obj.getInfo());
        }
        logActivity(currentUser.getRole() + " tracked objects of type: " + type);
    }

    // Helper method to safely parse integers
    private int parseInt(String val) {
        try {
            return Integer.parseInt(val.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    // Helper method to safely parse doubles
    private double parseDouble(String val) {
        try {
            return Double.parseDouble(val.trim());
        } catch (Exception e) {
            return 0.0;
        }
    }

    public List<SpaceObject> getObjects() {
        return objects;
    }

    /**
     * Retrieves the type of the currently active user
     * @return the type of user as a {@code String}
     */
    public String getUserType(){
        return currentUser != null ? currentUser.getRole() : "Unknown";
    }
    /**Logs the user's activity to the appropriate log file */
    public void logActivity(String action){
        try (FileWriter fw = new FileWriter(logFilePath, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println("[" + new Date() + "] " + action);
        } catch (IOException e) {
            System.out.println("Error writing to log: " + e.getMessage());
        }
    }


}
