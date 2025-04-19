package src;

/**
 * Represents a Scientist user who can track space objects.
 */
public class Scientist extends User {

    private MissionControl control;

    /**
     * Constructs a Scientist with the given username.
     *
     * @param username The scientist's username.
     */
    public Scientist(String username) {
        super(username, "Scientist");
    }

    /**
     * Associates this Scientist with a MissionControl instance.
     *
     * @param control The mission control system to interact with.
     */
    public void setMissionControl(MissionControl control) {
        this.control = control;
    }

    /**
     * Allows the scientist to track objects in space.
     * This delegates the logic to MissionControl.
     */
    public void trackObjects() {
        if (control != null) {
            control.trackObjects();
        } else {
            System.out.println("MissionControl not set for this Scientist.");
        }
    }

    /**
     * Allows the scientist to assess orbital status of objects.
     */
    public void assessOrbitStatus() {
        if (control != null) {
            control.accessOrbitalStatus();
        } else {
            System.out.println("MissionControl not set for this Scientist.");
        }
    }
}
