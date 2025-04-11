package src;
/**
 * Represents a space debris object.
 */
public class Debris extends SpaceObject {
    public Debris(){
        this.name = name; 
    }
    @Override
    public String getInfo(){
        return "Debris: " + name;
    }
}

