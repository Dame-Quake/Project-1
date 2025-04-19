package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import src.MissionControl;

/**
 * The {@code CSVFileReader} class is responsible for reading and parsing CSV files 
 * that contain data related to space objects such as debris, payloads, satellites, 
 * and unknown objects. 
 * <p>
 * This class loads data from a CSV file into the system and prepares it for tracking 
 * and analysis within the {@link MissionControl} system.
 * </p>
 *
 * @author Jorge Chavarria, Damian Villarreal
 * @version 1.0
 * @since 2025-04-12
 */
public class CSVFileReader {
    private final Map<String, List<SpaceObject>> spaceObjectsMap = new HashMap<>();
    private final String filePath = "resources/rso_metrics.csv";

    //column headers: 
    // record_id,norad_cat_id,satellite_name,country,
    //approximate_orbit_type,object_type,launch_year,launch_site,
    //longitude,avg_longitude,geohash,HRR_Category,
    //is_nominated,nominated_at,has_dossier,last_updated_at,
    //justification,focused_analysis,days_old,conjunction_count,
    //is_unk_object,all_maneuvers,days_since_ob,recent_maneuvers,
    //deltaV_90day,has_sister_debris



    /**
     * Reads the CSV file and populates the space objects map.
     * 
     * @throws IOException if an error occurs while reading the file
     */
    public Map<String, List<SpaceObject>> readCSVFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                // Skip header
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] fields = line.split(",", -1);
                // Ensure there are at least 26 fields, filling with empty strings if necessary
                if (fields.length < 26) {
                    String[] extendedFields = Arrays.copyOf(fields, 26);
                    for (int i = fields.length; i < 26; i++) {
                        extendedFields[i] = "";
                    }
                    fields = extendedFields;
                }
                
                //for debugging purposes
                // System.out.println("DEBUG: Raw CSV line:");
                // for (int i = 0; i < fields.length; i++) {
                //     System.out.println("Field[" + i + "]: '" + fields[i] + "'");
                // }
                int recordID = parseIntSafe(fields[0], "recordID");
                int noradCatID = parseIntSafe(fields[1], "norad_cat_id");
                String satelliteName = safeTrim(fields[2], "satellite_name");
                String country = safeTrim(fields[3], "country");
                String approximate_orbitType = safeTrim(fields[4], "approximate_orbit_type");
                String objectType = safeTrim(fields[5], "object_type");
                int launchYear = parseIntSafe(fields[6], "launch_year");
                String launchSite = safeTrim(fields[7], "launch_site");
                Double longitude = parseDoubleSafe(fields[8], "longitude");
                Double avgLongitude = parseDoubleSafe(fields[9], "avg_longitude");
                String geohash = safeTrim(fields[10], "geohash");
                String HRR_Category = safeTrim(fields[11], "HRR_Category");
                boolean isNominated = parseBooleanSafe(fields[12], "is_nominated");
                String nominatedAt = safeTrim(fields[13], "nominated_at");
                boolean hasDossier = parseBooleanSafe(fields[14], "has_dossier");
                String lastUpdatedAt = safeTrim(fields[15], "last_updated_at");
                String justification = safeTrim(fields[16], "justification");
                String focusedAnalysis = safeTrim(fields[17], "focused_analysis");
                int daysOld = parseIntSafe(fields[18], "days_old");
                int conjunctionCount = parseIntSafe(fields[19], "conjunction_count");
                boolean isUnkObject = parseBooleanSafe(fields[20], "is_unk_object");
                String allManeuvers = safeTrim(fields[21], "all_maneuvers");
                String daysSinceOb = safeTrim(fields[22], "days_since_ob");
                String recentManeuvers = safeTrim(fields[23], "recent_maneuvers");
                String deltaV90Day = safeTrim(fields[24], "deltaV_90day");
                boolean hasSisterDebris = parseBooleanSafe(fields[25], "has_sister_debris");


                SpaceObject spaceObject;
                    switch (objectType.toLowerCase()){
                    case "debris":
                        spaceObject = new Debris();
                        break;
                    case "payload":
                        spaceObject = new Payload();
                        break;
                    case "rocket body":
                        spaceObject = new RocketBody();
                        break;
                    default:
                        spaceObject = new Unknown();
                        break;
                    }
                

                
                spaceObject.recordID = recordID;
                spaceObject.noradCatID = noradCatID;
                spaceObject.satelliteName = satelliteName;
                spaceObject.country = country;
                spaceObject.approximate_orbitType = approximate_orbitType;
                spaceObject.objectType = objectType;
                spaceObject.launchYear = launchYear;
                spaceObject.launchSite = launchSite;
                spaceObject.longitude = longitude;
                spaceObject.avgLongitude = avgLongitude;
                spaceObject.geohash = geohash;
                spaceObject.HRR_Category = HRR_Category;
                spaceObject.isNominated = isNominated;
                spaceObject.nominatedAt = nominatedAt;
                spaceObject.hasDossier = hasDossier;
                spaceObject.lastUpdatedAt = lastUpdatedAt;
                spaceObject.justification = justification;
                spaceObject.focusedAnalysis = focusedAnalysis;
                spaceObject.daysOld = daysOld;
                spaceObject.conjunctionCount = conjunctionCount;
                spaceObject.isUnkObject = isUnkObject;
                spaceObject.allManeuvers = allManeuvers;
                spaceObject.daysSinceOb = daysSinceOb;
                spaceObject.recentManeuvers = recentManeuvers;
                spaceObject.deltaV90Day = deltaV90Day;
                spaceObject.hasSisterDebris = hasSisterDebris;

                // Add object to the categorized map
                spaceObjectsMap
                    .computeIfAbsent(objectType.toUpperCase(), k -> new ArrayList<>())
                    .add(spaceObject);

            }
        }
        return spaceObjectsMap;
    }

    private int parseIntSafe(String s, String fieldName) {
        // System.out.println("CALLING parseIntSafe for field: " + fieldName + " → '" + s + "'");
        if (s == null || s.trim().isEmpty()) {
            // System.out.println("Notice: Field [" + fieldName + "] was empty. Defaulting to 0.");
            return 0;
        }
        try {
            int result = Integer.parseInt(s.trim());
            // System.out.println("Parsed value for " + fieldName + ": " + result);
            return result;
        } catch (NumberFormatException e) {
            // System.out.println("Warning: Field [" + fieldName + "] had invalid integer: '" + s + "'. Defaulting to 0.");
            return 0;
        }
    }
    
    
    private double parseDoubleSafe(String s, String fieldName) {
        if (s == null || s.trim().isEmpty()) {
            // System.out.println("Notice: Field [" + fieldName + "] was empty. Defaulting to 0.0.");
            return 0.0;
        }
        try {
            return Double.parseDouble(s.trim());
        } catch (NumberFormatException e) {
            // System.out.println("Warning: Field [" + fieldName + "] had invalid double: '" + s + "'. Defaulting to 0.0.");
            return 0.0;
        }
    }
    
    private boolean parseBooleanSafe(String s, String fieldName) {
        if (s == null || s.trim().isEmpty()) {
            // System.out.println("Notice: Field [" + fieldName + "] was empty. Defaulting to false.");
            return false;
        }
        return s.trim().equalsIgnoreCase("true");
    }
    
    private String safeTrim(String s, String fieldName) {
        if (s == null || s.trim().isEmpty()) {
            // System.out.println("Notice: Field [" + fieldName + "] was empty. Defaulting to empty string.");
            return "";
        }
        return s.trim();
    }
    
    
}