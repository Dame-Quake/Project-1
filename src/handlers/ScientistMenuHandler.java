package src.handlers;

import src.interfaces.UserMenuHandler;

import java.util.Map;

import src.MissionControl;

public class ScientistMenuHandler implements UserMenuHandler {
    private MissionControl control;
    private Runnable exitCallback;

    private static final String[] MENU_OPTIONS = {
        "___________________________________",
        "Scientist Menu Options:",
        "1. Track Space Objects by Type",
        "2. Access Orbital Status of Space Objects",
        "0. Back to Main Menu"
    };

    //javadoc comment goes here
    public ScientistMenuHandler(MissionControl control, Runnable exitCallback) {
        this.control = control;
        this.exitCallback = exitCallback;
    }

    //javadoc comment goes here
    @Override
    public void printMenuOptions() {
        for (String line : MENU_OPTIONS) {
            System.out.println(line);
        }
    }

    private Map<String, Runnable> scientistActions = Map.of(
    "1", () -> control.performTracking(),
    "2", () -> control.accessOrbitalStatus(),
    "0", () -> {
            System.out.println("Returning to Main Menu...");
            control.logActivity();
            exitCallback.run();
        }
    );
        //javadoc comment goes here
    @Override
    public void handleUserChoice(String input) {
            scientistActions.getOrDefault(input, () -> 
            System.out.println("Invalid choice. Please try again.")
        ).run();
    }
}
