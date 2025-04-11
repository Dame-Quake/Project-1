package src;
/**
 * Represents a space debris object.
 */
public class Debris extends SpaceObject {
    @Override
    public String getInfo(){
        return "Debris: " + name;
    }
}

