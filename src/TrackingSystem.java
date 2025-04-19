package src;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides functionality for tracking and filtering space objects by type.
 */
public class TrackingSystem {

    private List<SpaceObject> objects = new ArrayList<>();

    /**
     * Sets the list of space objects to be tracked.
     *
     * @param objs The list of objects passed from MissionControl
     */
    public void setObjects(List<SpaceObject> objs) {
        this.objects = objs;
    }

    public List<SpaceObject> trackByType(String type){
        List<SpaceObject> filtered = new ArrayList<>();
        for (SpaceObject obj : objects) {
            if (obj instanceof Debris && type.equalsIgnoreCase("DEBRIS")) {
                filtered.add(obj);
            } else if (obj instanceof Payload && type.equalsIgnoreCase("PAYLOAD")) {
                filtered.add(obj);
            } else if (obj instanceof RocketBody && type.equalsIgnoreCase("ROCKET BODY")) {
                filtered.add(obj);
            } else if (obj instanceof Unknown && type.equalsIgnoreCase("UNKNOWN")) {
                filtered.add(obj);
            }
        }
        return filtered;
    }
    

    public List<SpaceObject> getAllInLEO(){
        List<SpaceObject> inLEO = new ArrayList<>();
        for (SpaceObject obj : objects) {
            if (obj.orbitType != null && obj.orbitType.equalsIgnoreCase("LEO")) {
                inLEO.add(obj);
            }
        }
        return inLEO;
    }
}
