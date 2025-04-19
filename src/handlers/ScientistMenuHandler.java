package src.handlers;

import src.interfaces.UserMenuHandler;

import java.util.Map;

import src.MissionControl;

/**
 * Handles the menu interaction logic for Scientist users.
 */
public class ScientistMenuHandler implements UserMenuHandler {
    private MissionControl control;
    private Runnable exitCallback;

    private static final String[] MENU_OPTIONS = {
        "___________________________________",
        "Scientist Menu Options:",
        "1. Track Space Objects by Type",
        "2. Access Orbital Status of Space Objects",
        "x. Back to Main Menu"
    };

    //javadoc comment goes here
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

    //javadoc comment goes here
    /**
     * Displays the menu options available to the Scientist user.
     */
    @Override
    public void printMenuOptions() {
        for (String line : MENU_OPTIONS) {
            System.out.println(line);
        }
    }

    private Map<String, Runnable> scientistActions = Map.of(
        "1", () -> control.trackObjects(),
        "2", () -> control.accessOrbitalStatus(),
        "x", () -> {
                System.out.println("Returning to Main Menu...");
                control.logActivity("Return to Main menu from Scientist Menu");
                exitCallback.run();
            }
    );
        //javadoc comment goes here
    /**
     * Handles the user's selected option from the menu.
     *
     * @param input the user's input
     */
    @Override
    public void handleUserChoice(String input) {
            scientistActions.getOrDefault(input, () -> 
            System.out.println("Invalid choice. Please try again.")
        ).run();
    }
}
