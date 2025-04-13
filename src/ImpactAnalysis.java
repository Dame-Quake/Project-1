package src;
import java.util.*;

/**
 * Provides methods to assess the orbital status and risk levels
 * of space debris and generate corresponding reports.
 */
public class ImpactAnalysis {
    
     /** Path to write the updated CSV file. */
    private String outputCSVPath;

    /** Path to write the exited debris TXT report. */
    private String reportFilePath;

     /**
     * Assesses whether each space object is still in orbit based on defined criteria.
     *
     * @param obj List of SpaceObject instances to assess.
     * @return A list of SpaceObjects with orbit status and risk levels annotated.
     */
    public List<SpaceObject> assessOrbitStatus(List<SpaceObject> obj){
        return null;
    }

    /**
     * Computes the risk level of a single space object based on orbital drift.
     *
     * @param obj A SpaceObject to assess.
     * @return A string representing the risk level: "Low", "Moderate", or "High".
     */
    public String computeRisk(SpaceObject obj){
        return null;
    }

    /**
     * Writes an updated CSV file including orbit status and risk level for a given object.
     *
     * @param obj  The SpaceObject to include in the CSV.
     * @param path The file path where the CSV should be written.
     */
    public void writeUpdatedCSV(SpaceObject obj, String path){
        // Implementation -->
    }

    /**
     * Generates a TXT report for debris that has exited orbit.
     *
     * @param obj  The SpaceObject to include in the report.
     * @param path The file path where the report should be saved.
     */
    public void generateExitReport(SpaceObject obj, String path){
        // Implementation -->
    }
}
