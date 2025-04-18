package src.handlers;

import src.interfaces.UserMenuHandler;
import src.MissionControl;

/**
 * Handles the menu interaction logic for Scientist users.
 */
public class ScientistMenuHandler implements UserMenuHandler {
    private MissionControl control;
    private Runnable exitCallback;

    /**
     * Constructs a new ScientistMenuHandler.
     *
     * @param control the mission control instance
     * @param exitCallback the callback to execute when exiting the menu
     */
    public ScientistMenuHandler(MissionControl control, Runnable exitCallback) {
        this.control = control;
        this.exitCallback = exitCallback;
    }

    /**
     * Displays the menu options available to the Scientist user.
     */
    @Override
    public void printMenuOptions() {
        System.out.println("___________________________________");
        System.out.println("Scientist Menu Options:");
        System.out.println("1. Track Space Objects by Type");
        System.out.println("2. Assess Orbital Status (coming soon)"); 
        System.out.println("0. Exit");
    }

    /**
     * Handles the user's selected option from the menu.
     *
     * @param input the user's input
     */
    @Override
    public void handleUserChoice(String input) {
        switch (input) {
            case "1":
                control.trackObjects(); // Shows filtered object types
                break;
            case "2":
                control.performTracking(); // Placeholder or to be implemented
                break;
            case "0":
                exitCallback.run();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
