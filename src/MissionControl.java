package src;

import java.util.*;
import java.io.*;

/**
 * @author Jorge Chavarria Damian Villarreal
 * @version 1.0
 * @since 2025-04-9
 *        The {@code MissionControl} class manages the coordination of space
 *        object tracking,
 *        user sessions, and system logging. It acts as the central controller
 *        for the application,
 *        linking together users, tracking systems, and data operations.
 */
public class MissionControl {

    /** Dictionary of space objects being tracked. */
    private Map<String, List<SpaceObject>> spaceObjectsByType;
    /** The current user of the system. */
    private User currentUser;
    /** The path to the log file. */
    private String logFilePath = "logs\\logfile.txt";
    /** Logfile to keep logging consistant */
    private LogFile logger;
    /** The tracking system used for tracking space objects. */
    private TrackingSystem trackingSystem;

    /**
     * Constructs a new {@code MissionControl} instance with the provided tracking
     * system.
     *
     * @param trackingSystem the tracking system to be used by this controller
     */

    public MissionControl(TrackingSystem trackingSystem, Map<String, List<SpaceObject>> loadedObjects) {
        this.trackingSystem = trackingSystem;
        this.spaceObjectsByType = loadedObjects;
    }

    /** Sets the user currently interacting with the system */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    /**
     * Injects a {@link LogFile} instance into this {@code MissionControl} object
     * for consistent system-wide logging of user actions and events.
     *
     * @param logger the {@code LogFile} instance used to log system activity
     */
    public void setLogger(LogFile logger) {
        this.logger = logger;
    }

    /**
     * Scientist option: Initiates object tracking based on their types using the
     * tracking system
     */
    public void performTracking() {
        logger.logActivity("Performing tracking of space objects by type...");
        // trackingSystem.trackByType();
    }

    /** Scientist option: Retrieves all objects in Low Earth Orbit (LEO) */
    public void accessOrbitalStatus() {
        Scanner scanner = new Scanner(System.in);
        List<SpaceObject> allObjects = new ArrayList<>();
        spaceObjectsByType.values().forEach(allObjects::addAll);

        while (true) {
            System.out.println("\nAssess Orbit Status:");
            System.out.println("1. Track Objects in LEO");
            System.out.println("2. Assess if debris is still in orbit");
            System.out.println("3. Back");
            System.out.print("Choice: ");
            String input = scanner.nextLine();

            if (input.equals("1")) {
                List<SpaceObject> leoObjects = new ArrayList<>();
                for (SpaceObject obj : allObjects) {
                    if (obj.approximate_orbitType != null && obj.approximate_orbitType.equalsIgnoreCase("LEO")) {
                        leoObjects.add(obj);
                    }
                }
                if (leoObjects.isEmpty()) {
                    System.out.println("No objects in LEO.");
                } else {
                    for (SpaceObject obj : leoObjects) {
                        System.out.printf(
                                "ID: %d | Name: %s | Country: %s | Orbit: %s | Year: %d | Site: %s | Lon: %.2f | AvgLon: %.2f | Geohash: %s | Days Old: %d%n",
                                obj.recordID, obj.satelliteName, obj.country, obj.approximate_orbitType,
                                obj.launchYear, obj.launchSite, obj.longitude, obj.avgLongitude,
                                obj.geohash, obj.daysOld);
                    }
                }

            } else if (input.equals("2")) {
                List<SpaceObject> exitedDebris = new ArrayList<>();

                try (FileWriter csvOut = new FileWriter("output/assessed_orbit_status.csv");
                        FileWriter txtOut = new FileWriter("output/exited_debris_report.txt")) {

                    csvOut.write(
                            "record_id,satellite_name,country,approximate_orbit_type,object_type,launch_year,launch_site,longitude,avg_longitude,geohash,days_old,conjunction_count,still_in_orbit,risk_level\n");

                    int inOrbit = 0, exited = 0;
                    for (SpaceObject obj : allObjects) {
                        boolean stillInOrbit = obj.approximate_orbitType != null &&
                                !obj.approximate_orbitType.isEmpty() &&
                                obj.longitude != 0 &&
                                obj.daysOld < 15000 &&
                                obj.conjunctionCount >= 1;

                        double drift = Math.abs(obj.longitude - obj.avgLongitude);
                        String riskLevel = "Low";
                        if (drift > 50)
                            riskLevel = "High";
                        else if (drift > 10)
                            riskLevel = "Moderate";

                        csvOut.write(String.format("%d,%s,%s,%s,%s,%d,%s,%.2f,%.2f,%s,%d,%d,%s,%s\n",
                                obj.recordID, obj.satelliteName, obj.country, obj.approximate_orbitType,
                                obj.getClass().getSimpleName(), obj.launchYear, obj.launchSite,
                                obj.longitude, obj.avgLongitude, obj.geohash,
                                obj.daysOld, obj.conjunctionCount, stillInOrbit, riskLevel));

                        if (stillInOrbit) {
                            inOrbit++;
                        } else {
                            exited++;
                            exitedDebris.add(obj);
                        }
                    }

                    txtOut.write("Debris that has exited orbit:\n");
                    for (SpaceObject obj : exitedDebris) {
                        txtOut.write(String.format(
                                "ID: %d | Name: %s | Country: %s | Orbit: %s | Year: %d | Site: %s | Lon: %.2f | AvgLon: %.2f | Geohash: %s | Days Old: %d\n",
                                obj.recordID, obj.satelliteName, obj.country, obj.approximate_orbitType,
                                obj.launchYear, obj.launchSite, obj.longitude, obj.avgLongitude,
                                obj.geohash, obj.daysOld));
                    }
                    txtOut.write("\nTotal still in orbit: " + inOrbit + "\n");
                    txtOut.write("Total exited: " + exited + "\n");

                    System.out.println("Orbit assessment completed. Files saved to output/ directory.");

                } catch (IOException e) {
                    System.out.println("Error writing to output files: " + e.getMessage());
                }

            } else if (input.equals("3")) {
                return;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    /** Loads external or persisted data into the mission control system */
    public void loadData() {
        logger.logActivity("Loading external data into the system...");
        // Implementation of data loading logic

    }

    /**
     * Retrieves all space objects of a specific type from the system
     * 
     * @param type the type of space object to retrieve
     * @return a list of space objects of the specified type
     */
    public List<SpaceObject> getObjectsByType(String type) {
        return spaceObjectsByType.getOrDefault(type.toUpperCase(), new ArrayList<>());
    }

    /**
     * Retrieves all space objects from the system
     * 
     * @return a list of all space objects
     */
    public Map<String, List<SpaceObject>> getAllObjectsMap() {
        return spaceObjectsByType;
    }

    /** Tracks all registered space objects in the system */
    public void trackObjects() {
        Scanner scanner = new Scanner(System.in);
        logger.logActivity("Tracking all registered space objects...");

        while (true) {
            System.out.println("\nSelect object type to track:");
            System.out.println("1. Rocket Body");
            System.out.println("2. Debris");
            System.out.println("3. Payload");
            System.out.println("4. Unknown");
            System.out.println("x. Back");
            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();

            String type;
            switch (choice) {
                case "1":
                    type = "ROCKET BODY";
                    break;
                case "2":
                    type = "DEBRIS";
                    break;
                case "3":
                    type = "PAYLOAD";
                    break;
                case "4":
                    type = "UNKNOWN";
                    break;
                case "x":
                    logger.logActivity("Scientist returned to previous menu from 'Track Objects'.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }

            List<SpaceObject> filtered = trackingSystem.trackByType(type);

            if (filtered.isEmpty()) {
                System.out.println("No objects found of type: " + type);
                logger.logActivity("Scientist searched for " + type + " objects — none found.");
            } else {
                System.out.println("\n--- " + type + " Objects ---");
                for (SpaceObject obj : filtered) {
                    System.out.printf(
                            "ID: %s | Name: %s | Country: %s | Orbit: %s | Year: %d | Site: %s | Lon: %.2f | AvgLon: %.2f | Geohash: %s | Days Old: %d%n",
                            obj.recordID, obj.satelliteName, obj.country, obj.approximate_orbitType, obj.launchYear,
                            obj.launchSite,
                            obj.longitude, obj.avgLongitude, obj.geohash, obj.daysOld);
                }
                logger.logActivity("Scientist viewed " + filtered.size() + " " + type + " object(s).");
            }
        }
    }

    /**
     * Retrieves the type of the currently active user
     * 
     * @return the type of user as a {@code String}
     */
    public String getUserType() {
        return currentUser.getRole();
    }

    /** Logs the user's activity to the appropriate log file */
    public void logActivity(String action) {
        if (logger != null) {
            logger.logActivity(action);
        } else {
            System.out.println("Logger not set. Action: " + action);
        }
    }

}
