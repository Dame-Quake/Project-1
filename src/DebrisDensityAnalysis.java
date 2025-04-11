package src;
import java.util.*;

public class DebrisDensityAnalysis {
    
    private List<Double> analyzedAltitudes;
    private int densityThreshold;

    public DebrisDensityAnalysis(List<Double> analyzedAltitudes, int densityThreshold){
        this.analyzedAltitudes = analyzedAltitudes;
        this.densityThreshold = densityThreshold;

    }

    public Map<Double, Integer> calculateDensity(List<Object> objects){
        return null;
    }

    public void generalDensityReport(Map<Double, Integer> desites, String path){

    }

    public List<Double> getHighDensityZones(Map<Double, Integer> densities){
        return null;
    }



}
