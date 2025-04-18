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
    private String logFilePath = "logs\\logfile.txt";
    /** Logfile to keep logging consistant */
    private LogFile logger;
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

    /**Scientist option: Initiates object tracking based on their types using the tracking system */
    public void performTracking(){
        logger.logActivity("Performing tracking of space objects by type...");
        // trackingSystem.trackByType();
    }

    /**Space Agency Representative option: Retrieves all objects in Low Earth Orbit (LEO) */
    public Object accessOrbitalStatus() {
        logger.logActivity("Accessing orbital status of space objects...");
        return trackingSystem.getAllInLEO();    
    }

    /**Loads external or persisted data into the mission control system*/
    public void loadData(){
        logger.logActivity("Loading external data into the system...");
        //Implementation of data loading logic

    }

    /**Tracks all registered space objects in the system */
    public void trackObjects(){
        logger.logActivity("Tracking all registered space objects...");
        //implementation of tracking logic
    }

    /**
     * Retrieves the type of the currently active user
     * @return the type of user as a {@code String}
     */
    public String getUserType(){
        return currentUser.getRole();
    }
    /**Logs the user's activity to the appropriate log file */
    public void logActivity(String action){
        if (logger != null) {
            logger.logActivity(action);
        } else {
            System.out.println("Logger not set. Action: " + action);
        }
    }

    


}
