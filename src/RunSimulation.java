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
        this.logger = new LogFile("logs/logfile.txt");
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the user interaction and role selection process.
     */
    private void startSimulation() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        while (true) {
            System.out.println("\nSelect your role:");
            System.out.println("1. Scientist");
            System.out.println("2. Space Agency Representative");
            System.out.println("3. Policymaker");
            System.out.println("4. Administrator");
            System.out.println("0. Exit");

            System.out.print("Choice: ");
            String roleChoice = scanner.nextLine();

            switch (roleChoice) {
                case "1":
                    currentUser = new Scientist(name);
                    control.setCurrentUser(currentUser);
                    this.menuHandler = new ScientistMenuHandler(control, () -> running = false);
                    break;
                case "2":
                    System.out.println("Space Agency Representative role is under construction.");
                    continue;
                case "3":
                    System.out.println("Policymaker role is under construction.");
                    continue;
                case "4":
                    System.out.println("Administrator role is under construction.");
                    continue;
                case "0":
                    System.out.println("Exiting system. Goodbye.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }

            logger.log("User logged in: " + currentUser.getName() + " (" + currentUser.getRole() + ")");
            break;
        }

        runMenuLoop();
    }

    /**
     * Displays the main interaction menu and delegates input handling.
     */
    private void runMenuLoop() {
        running = true;
        while (running) {
            menuHandler.printMenuOptions();
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();
            logger.log("User " + currentUser.getName() + " (" + currentUser.getRole() + ") input: " + input);
            menuHandler.handleUserChoice(input);
        }
        System.out.println("Exiting simulation. Thanks for using the system. Goodbye!");
    }
}
