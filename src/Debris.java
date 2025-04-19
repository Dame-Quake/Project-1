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
        this.satelliteName = satelliteName; 
        this.recordID = recordID;
        this.country = country;
        this.approximate_orbitType = approximate_orbitType;
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
        return "Debris: " + satelliteName + 
               ", NORAD ID: " + noradCatID + 
               ", Country: " + country + 
               ", Orbit Type: " + approximate_orbitType + 
               ", Launch Year: " + launchYear + 
               ", Launch Site: " + launchSite +
               ", Longitude: " + longitude +
               ", Avg Longitude: " + avgLongitude +
               ", Geohash: " + geohash +
               ", Days Old: " + daysOld +
               ", Conjunction Count: " + conjunctionCount;
    }
}

