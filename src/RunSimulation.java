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
    private boolean running; // Indicates if the simulation is running
    private boolean inSession; // Indicates if the user is in a session
    private MissionControl control;
    private User currentUser;
    private LogFile logger;
    private Scanner scanner;
    private UserMenuHandler menuHandler;


    /** Main method to initialize the simulation environment and start the simulation */
    public static void main(String[] args) {
        RunSimulation sim = new RunSimulation();
        sim.startSimulation();
    }

    /**
     * Constructs a new {@code RunSimulation} instance and initializes the mission control system.
     */
    public RunSimulation() {
        this.control = new MissionControl(new TrackingSystem());
        this.logger = new LogFile("logs\\logfile.txt");
        this.control.setLogger(logger);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the user interaction and role selection process.
     */
    private void startSimulation() {
        inSession = true; // gets user back to role selection if they want to change roles
        while (inSession) {
            String input[] = User.promptForRoleInput();
            String username = input[0];
            String roleInput = input[1];

            // Check if the user wants to exit the simulation
            if(username.equalsIgnoreCase("x")) {
                System.out.println("Exiting simulation. Thanks for using the system. Goodbye!");
                return; // Exit the simulation
            }

            currentUser = UserFactory.createUser(username, roleInput);
            Role role = Role.fromInput(roleInput);

            if(role == null) {
                System.out.println("Invalid role input. Try Again.");
                continue;
            }
        
            logger.logActivity("User logged in: " + currentUser.getName() + " (" + currentUser.getRole() + ")");
        
            switch (roleInput) {
                case "1":
                    control.setCurrentUser(currentUser);
                    this.menuHandler = new ScientistMenuHandler(control, () -> {
                        // System.out.println("Returning to Main Menu...");
                        running = false; // Exit the menu loop
                    });
                    runMenuLoop();
                    break;
        
                // Other roles will me implemented later
                case "2":
                    System.out.println("Space Agency Representative role is under construction.\nTry again later.");
                    break;
                case "3":
                    System.out.println("Policymaker role is under construction.\nTry again later.");
                    break;
                case "4":
                    System.out.println("Admin role is under construction.\nTry again later.");
                    break;
                default:
                    System.out.println("This role is under construction.\nTry again later.");
            }
        }
        
    }
    
    /** Displays the main menu for user interaction 
     *  Displays the main interaction menu and delegates input handling.
     */
    private void runMenuLoop() {
        running = true;
        while (running) {
            menuHandler.printMenuOptions();
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();
            logger.logActivity("User " + currentUser.getName() + " (" + currentUser.getRole() + ") input: " + input);
            menuHandler.handleUserChoice(input);
        }
        System.out.println("Exiting simulation. Thanks for using the system. Goodbye!");
    }
}
