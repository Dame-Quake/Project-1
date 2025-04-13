package src;
/**
 * Represents a space debris object.
 * This class models orbital debris with associated tracking and metadata.
 */
public class Debris extends SpaceObject {

     /**
     * Default constructor for Debris.
     * Initializes inherited fields. Values must be assigned externally after creation
     * or passed through a more complete constructor if implemented.
     */
    public Debris(){
        this.name = name; 
        this.recordID = recordID;
        this.country = country;
        this.orbitType = orbitType;
        this.launchYear = launchYear;
        this.launchSite = launchSite;
        this.longitude = longitude;
        this.avgLongitude = avgLongitude;
        this.geohash = geohash;
        this.daysOld = daysOld;
        this.conjunctionCount = conjunctionCount;
    }

    /**
     * Returns formatted information specific to debris objects.
     *
     * @return A string describing the debris object.
     */
    @Override
    public String getInfo(){
        return "Debris: " + name;
    }
}

