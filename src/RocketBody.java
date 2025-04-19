package src;
/**
 * Represents a Rockey Body object in space.
 * Inherits properties from the abstract SpaceObject class.
 */
public class RocketBody extends SpaceObject {

    /**
     * Returns formatted information specific to Rockey Body objects.
     *
     * @return A string describing the Rockey Body.
     */
    @Override
    public String getInfo(){
        return "Rockey Body: " + satelliteName;
    }
}
