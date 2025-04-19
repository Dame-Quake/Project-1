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
    protected int recordID ;
    protected int noradCatID ;
    protected String satelliteName ;
    protected String country ;
    protected String approximate_orbitType ;
    protected String objectType ;
    protected int launchYear ;
    protected String launchSite;
    protected Double longitude;
    protected Double avgLongitude;
    protected String geohash ;
    protected String HRR_Category ;
    protected boolean isNominated;
    protected String nominatedAt ;
    protected boolean hasDossier;
    protected String lastUpdatedAt ;
    protected String justification ;
    protected String focusedAnalysis ;
    protected int daysOld;
    protected int conjunctionCount;
    protected boolean isUnkObject;
    protected String allManeuvers ;
    protected String daysSinceOb ;
    protected String recentManeuvers ;
    protected String deltaV90Day ;
    protected boolean hasSisterDebris;

    public abstract String getInfo();  
}
