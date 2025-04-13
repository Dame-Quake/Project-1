package src;
/**
 * Represents a satellite object in space.
 * Inherits properties from the abstract SpaceObject class.
 */
public class Satellite extends SpaceObject {

    /**
     * Returns formatted information specific to satellite objects.
     *
     * @return A string describing the satellite.
     */
    @Override
    public String getInfo(){
        return "Satellite: " + name;
    }
}
