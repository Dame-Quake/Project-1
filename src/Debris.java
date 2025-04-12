package src;
/**
 * Represents a space debris object.
 */
public class Debris extends SpaceObject {
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
    @Override
    public String getInfo(){
        return "Debris: " + name;
    }
}

