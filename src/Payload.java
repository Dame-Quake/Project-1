package src;
/**
 * Represents a payload object in space, such as instruments or cargo.
 * Inherits from the abstract SpaceObject class.
 */
public class Payload extends SpaceObject {

    /**
     * Returns formatted information specific to payload objects.
     *
     * @return A string describing the payload.
     */
    @Override
    public String getInfo(){
        return "Payload: " + satelliteName;
    }
}
