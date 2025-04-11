package src;
public abstract class SpaceObject {
    private String recordID;
    private String name;
    private String country;
    private String orbitType;
    private int launchYear;
    private String launchSite;
    private double longitude;
    private double avgLongitude;
    private String geohash;
    private int daysOld;
    private int conjunctionCount;

    public String getInfo(){
        return "Null";
    }
}
