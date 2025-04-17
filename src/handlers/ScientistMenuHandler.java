package src.handlers;

import src.interfaces.UserMenuHandler;
import src.MissionControl;

public class ScientistMenuHandler implements UserMenuHandler {
    private MissionControl control;
    private Runnable exitCallback;

    public ScientistMenuHandler(MissionControl control, Runnable exitCallback) {
        this.control = control;
        this.exitCallback = exitCallback;
    }
    @Override
    public void printMenuOptions() {
        System.out.println("Scientist Menu Options:");
        System.out.println("1. Track Space Objects by Type");
        System.out.println("2. Access Orbital Status of Space Objects"); 
        System.out.println("0. Exit");
    }
    @Override
    public void handleUserChoice(String input) {
        switch (input) {
            case "1":
                control.performTracking();
                break;
            case "2":
                control.trackObjects();
                break;
            case "0":
                exitCallback.run();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
