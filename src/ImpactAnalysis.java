package src;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods to assess the orbital status and risk levels
 * of space debris and generate corresponding reports.
 */
public class ImpactAnalysis {

    /** Path to write the updated CSV file. */
    private String outputCSVPath = "output/assessed_orbit_status.csv";

    /** Path to write the exited debris TXT report. */
    private String reportFilePath = "output/exited_debris_report.txt";

    /**
     * Assesses whether each space object is still in orbit based on defined criteria.
     *
     * @param objects List of SpaceObject instances to assess.
     * @return A list of SpaceObjects with orbit status and risk levels annotated.
     */
    public List<SpaceObject> assessOrbitStatus(List<SpaceObject> objects) {
        List<SpaceObject> exitedObjects = new ArrayList<>();

        try (FileWriter csvOut = new FileWriter(outputCSVPath);
             FileWriter txtOut = new FileWriter(reportFilePath)) {

            csvOut.write("record_id,satellite_name,country,approximate_orbit_type,object_type,launch_year,launch_site,longitude,avg_longitude,geohash,days_old,conjunction_count,still_in_orbit,risk_level\n");

            int inOrbit = 0, exited = 0;

            for (SpaceObject obj : objects) {
                boolean stillInOrbit = obj.approximate_orbitType != null &&
                        !obj.approximate_orbitType.equalsIgnoreCase("Unknown Orbit Category") &&
                        obj.longitude != 0 &&
                        obj.daysOld < 15000 &&
                        obj.conjunctionCount >= 1;

                String riskLevel = computeRisk(obj);

                csvOut.write(String.format("%d,%s,%s,%s,%s,%d,%s,%.2f,%.2f,%s,%d,%d,%s,%s\n",
                        obj.recordID, obj.satelliteName, obj.country, obj.approximate_orbitType,
                        obj.objectType, obj.launchYear, obj.launchSite,
                        obj.longitude, obj.avgLongitude, obj.geohash,
                        obj.daysOld, obj.conjunctionCount, stillInOrbit, riskLevel));

                if (stillInOrbit) {
                    inOrbit++;
                } else {
                    exited++;
                    exitedObjects.add(obj);
                }
            }

            // Write TXT report
            txtOut.write("Debris that has exited orbit:\n");
            for (SpaceObject obj : exitedObjects) {
                txtOut.write(String.format(
                        "ID: %d | Name: %s | Country: %s | Orbit: %s | Year: %d | Site: %s | Lon: %.2f | AvgLon: %.2f | Geohash: %s | Days Old: %d\n",
                        obj.recordID, obj.satelliteName, obj.country, obj.approximate_orbitType,
                        obj.launchYear, obj.launchSite, obj.longitude, obj.avgLongitude, obj.geohash, obj.daysOld));
            }

            txtOut.write("\nTotal still in orbit: " + inOrbit + "\n");
            txtOut.write("Total exited: " + exited + "\n");

            System.out.println("Orbit status assessment completed and saved to output folder.");

        } catch (IOException e) {
            System.out.println("Error writing reports: " + e.getMessage());
        }

        return exitedObjects;
    }

    /**
     * Computes the risk level of a single space object based on orbital drift.
     *
     * @param obj A SpaceObject to assess.
     * @return A string representing the risk level: "Low", "Moderate", or "High".
     */
    public String computeRisk(SpaceObject obj) {
        double drift = Math.abs(obj.longitude - obj.avgLongitude);
        if (drift > 50) return "High";
        else if (drift > 10) return "Moderate";
        return "Low";
    }

    /**
     * Writes an updated CSV file including orbit status and risk level for a given object.
     *
     * @param obj  The SpaceObject to include in the CSV.
     * @param path The file path where the CSV should be written.
     */
    public void writeUpdatedCSV(SpaceObject obj, String path) {
        try (FileWriter writer = new FileWriter(path, true)) {
            String riskLevel = computeRisk(obj);
            boolean stillInOrbit = obj.approximate_orbitType != null &&
                    obj.longitude != 0 &&
                    obj.daysOld < 15000 &&
                    obj.conjunctionCount >= 1;

            writer.write(String.format("%d,%s,%s,%s,%s,%d,%s,%.2f,%.2f,%s,%d,%d,%s,%s\n",
                    obj.recordID, obj.satelliteName, obj.country, obj.approximate_orbitType,
                    obj.objectType, obj.launchYear, obj.launchSite,
                    obj.longitude, obj.avgLongitude, obj.geohash,
                    obj.daysOld, obj.conjunctionCount, stillInOrbit, riskLevel));
        } catch (IOException e) {
            System.out.println("Error writing to updated CSV: " + e.getMessage());
        }
    }

    /**
     * Generates a TXT report for debris that has exited orbit.
     *
     * @param exitedObjects List of SpaceObjects that exited orbit.
     * @param path          The file path where the report should be saved.
     */
    public void generateExitReport(List<SpaceObject> exitedObjects, String path) {
        try (FileWriter writer = new FileWriter(path)) {
            for (SpaceObject obj : exitedObjects) {
                writer.write(String.format(
                        "ID: %d | Name: %s | Country: %s | Orbit: %s | Year: %d | Site: %s | Lon: %.2f | AvgLon: %.2f | Geohash: %s | Days Old: %d\n",
                        obj.recordID, obj.satelliteName, obj.country, obj.approximate_orbitType,
                        obj.launchYear, obj.launchSite, obj.longitude, obj.avgLongitude, obj.geohash, obj.daysOld));
            }
        } catch (IOException e) {
            System.out.println("Error generating exit report: " + e.getMessage());
        }
    }
}