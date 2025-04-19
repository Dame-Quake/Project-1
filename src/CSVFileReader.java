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
    private final String filePath = "resources\\rso_metrics.csv";

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
                

                int recordID = Integer.parseInt(fields[0].trim());
                int noradCatID = Integer.parseInt(fields[1].trim());
                String satelliteName = fields[2].trim();
                String country = fields[3].trim();
                String approximate_orbitType = fields[4].trim();
                String objectType = fields[5].trim();
                int launchYear = Integer.parseInt(fields[6].trim());
                String launchSite = fields[7].trim();
                Double longitude = Double.parseDouble(fields[8].trim());
                Double avgLongitude = Double.parseDouble(fields[9].trim());
                String geohash = fields[10].trim();
                String HRR_Category = fields[11].trim();
                boolean isNominated = Boolean.parseBoolean(fields[12].trim());
                String nominatedAt = fields[13].trim();
                boolean hasDossier = Boolean.parseBoolean(fields[14].trim());
                String lastUpdatedAt = fields[15].trim();
                String justification = fields[16].trim();
                String focusedAnalysis = fields[17].trim();
                int daysOld = Integer.parseInt(fields[18].trim());
                int conjunctionCount = Integer.parseInt(fields[19].trim());
                boolean isUnkObject = Boolean.parseBoolean(fields[20].trim());
                String allManeuvers = fields[21].trim();
                String daysSinceOb = fields[22].trim();
                String recentManeuvers = fields[23].trim();
                String deltaV90Day = fields[24].trim();
                boolean hasSisterDebris = Boolean.parseBoolean(fields[25].trim());

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
                    .computeIfAbsent(objectType, k -> new ArrayList<>())
                    .add(spaceObject);

            }
        }
        return spaceObjectsMap;
    }







}
