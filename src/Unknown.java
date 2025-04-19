package src;
public class Unknown extends SpaceObject {
    @Override
    public String getInfo(){
        return "Unknown Object: " + satelliteName + 
               ", NORAD ID: " + noradCatID + 
               ", Country: " + country + 
               ", Launch Year: " + launchYear + 
               ", Object Type: " + objectType;
    }
}
