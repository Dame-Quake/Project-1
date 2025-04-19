package src;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Provides functionality to analyze space debris density
 * across different orbital altitude ranges.
 */
public class DebrisDensityAnalysis {

    /** List of altitudes that have been analyzed. */
    private List<Double> analyzedAltitudes;

    /** Threshold above which a region is considered high density. */
    private int densityThreshold;

    /**
     * Constructs a DebrisDensityAnalysis instance.
     *
     * @param analyzedAltitudes List of altitude bands to be analyzed.
     * @param densityThreshold  Threshold for classifying high-density zones.
     */
    public DebrisDensityAnalysis(List<Double> analyzedAltitudes, int densityThreshold) {
        this.analyzedAltitudes = analyzedAltitudes;
        this.densityThreshold = densityThreshold;
    }

    /**
     * Calculates debris density for each altitude band.
     *
     * @param objects List of space objects to analyze (expected to be Debris or Satellite).
     * @return A map of altitude to number of objects present in that band.
     */
    public Map<Double, Integer> calculateDensity(List<Object> objects) {
        Map<Double, Integer> densityMap = new HashMap<>();

        for (Object o : objects) {
            if (o instanceof SpaceObject) {
                SpaceObject obj = (SpaceObject) o;
                double altitude = obj.longitude; // Using longitude as a stand-in for altitude
                double band = Math.floor(altitude / 100) * 100; // Group into 100 km altitude bands

                densityMap.put(band, densityMap.getOrDefault(band, 0) + 1);
            }
        }

        return densityMap;
    }

    /**
     * Generates a density report file based on the calculated density map.
     *
     * @param densities A map of altitude to object count.
     * @param path      Path to output the density report.
     */
    public void generalDensityReport(Map<Double, Integer> densities, String path) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            writer.println("Altitude Band (km), Object Count");

            for (Map.Entry<Double, Integer> entry : densities.entrySet()) {
                writer.printf("%.0f km, %d%n", entry.getKey(), entry.getValue());
            }

            writer.flush();
            System.out.println("Density report generated at: " + path);

        } catch (IOException e) {
            System.err.println("Failed to write density report: " + e.getMessage());
        }
    }

    /**
     * Returns a list of altitude zones where debris density exceeds the threshold.
     *
     * @param densities A map of altitude to object count.
     * @return A list of altitudes classified as high-density zones.
     */
    public List<Double> getHighDensityZones(Map<Double, Integer> densities) {
        List<Double> highZones = new ArrayList<>();

        for (Map.Entry<Double, Integer> entry : densities.entrySet()) {
            if (entry.getValue() >= densityThreshold) {
                highZones.add(entry.getKey());
            }
        }

        return highZones;
    }
}