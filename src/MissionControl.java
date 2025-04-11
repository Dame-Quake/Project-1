package src;
import java.util.*;
public class MissionControl {
    private List<SpaceObject> objects;
    private User currentUser;
    private String logFilePath;
    private TrackingSystem trackingSystem;

    public MissionControl(TrackingSystem trackingSystem){
        this.trackingSystem = trackingSystem;
    }

    public void setCurrentUser(User user){
        this.currentUser = user;
    }

    public void performTracking(){
        trackingSystem.trackByType();
    }

    public void loadData(){}

    public void trackObjects(){}

    public String getUserType(){
        return null;
    }

    public void logActivity(){}


}
