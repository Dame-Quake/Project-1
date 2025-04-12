package src;
/**
 * The {@code RunSimulation} class serves as the entry point for the application.
 * It initializes the necessary components and starts the simulation process.
 *
 * @author John Chavarria Damian Villarreal
 * @since 2025-04-9
 * @version 1.0
 */
public class RunSimulation {
    private MissionControl control;
    private boolean running;

    /** Main method to initialize the simulation environment and starts the simulation */
    public static void main(String[] args){
        RunSimulation sim = new RunSimulation();
        sim.displayMainMenu();
    }
    
    /**
     * Constructs a new {@code RunSimulation} instance and initializes the mission control system.
     */
    public RunSimulation(){
        this.control = new MissionControl(new TrackingSystem());
    }

    /** Displays the main menu for user interaction */
    public void displayMainMenu(){}

    /** Handles user choices and interactions */
    public void handleUserChoice(String choice){}
}
