package src;
/**
 * Represents a general space object with common attributes such as identification,
 * origin, orbital characteristics, and tracking information.
 * <p>
 * This abstract class serves as a base for specific types of space objects,
 * providing shared properties and enforcing the implementation of the {@code getInfo()} method.
 * </p>
 */
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
