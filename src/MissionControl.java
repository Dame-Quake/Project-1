package src;
import java.util.*;
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
    private List<SpaceObject> objects;
    /** The current user of the system.*/
    private User currentUser;
    /** The path to the log file.*/
    private String logFilePath;
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
        trackingSystem.trackByType();
    }
    /**Loads external or persisted data into the mission control system*/
    public void loadData(){}

    /**Tracks all registered space objects in the system */
    public void trackObjects(){}

    /**
     * Retrieves the type of the currently active user
     * @return the type of user as a {@code String}
     */
    public String getUserType(){
        return currentUser != null ? currentUser.getRole() : "Unknown";
        }

    /**Logs the user's activity to the appropriate log file */
    public void logActivity(){}


}
