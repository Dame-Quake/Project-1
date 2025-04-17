package src;

import src.enums.Role;
import src.handlers.ScientistMenuHandler;
import src.interfaces.UserMenuHandler;
import java.util.Scanner;

/**
 * The {@code RunSimulation} class serves as the entry point for the application.
 * It initializes the necessary components and starts the simulation process.
 *
 * @author John Chavarria Damian Villarreal
 * @since 2025-04-9
 * @version 1.0.1
 */
public class RunSimulation {
    private MissionControl control;
    private boolean running;
    private User currentUser;
    private LogFile logger;
    private Scanner scanner;
    private UserMenuHandler menuHandler;

    /** Main method to initialize the simulation environment and starts the simulation */
    public static void main(String[] args){
        RunSimulation sim = new RunSimulation();
        sim.startSimulation();
    }
        
    /**
     * Constructs a new {@code RunSimulation} instance and initializes the mission control system.
     */
    public RunSimulation(){
        this.control = new MissionControl(new TrackingSystem());
        this.logger = new LogFile("logs/logfile.txt");
        this.scanner = new Scanner(System.in);
    }

    private void startSimulation() {
        currentUser = User.identifyUser();
        Role role = Role.fromString(currentUser.getRole());
        //can implement user validation here with a simple if statement
        if(role == null || role != Role.SCIENTIST) {
            System.out.println("Invalid user role. Other roles are under construction.");
            return;
        }
        logger.log("User logged in: "+ currentUser.getName() + " (" + currentUser.getRole() + ")");
        if(role == Role.SCIENTIST) {
            control.setCurrentUser(currentUser);
            this.menuHandler = new ScientistMenuHandler(control, () -> running = false);
            
        } else {
            System.out.println("Role not implemented yet. Please try again later.");
            return;
        }
        runMenuLoop();
        
    }
    
    /** Displays the main menu for user interaction */
    private void runMenuLoop(){
        running = true;
        while(running){
            menuHandler.printMenuOptions();
            String input = scanner.nextLine();
            logger.log("User " + currentUser.getName() + " (" + currentUser.getRole() + ") " +"input: " + input);
            menuHandler.handleUserChoice(input);
        }
        System.out.println("Exiting simulation. Thanks Come Again. Goodbye!");
    }

}
