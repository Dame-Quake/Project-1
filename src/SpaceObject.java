package src;
public abstract class SpaceObject {
    protected String recordID;
    protected String name;
    protected String country;
    protected String orbitType;
    protected int launchYear;
    protected String launchSite;
    protected double longitude;
    protected double avgLongitude;
    protected String geohash;
    protected int daysOld;
    protected int conjunctionCount;

    public abstract String getInfo();  
}
