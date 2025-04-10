public class RunSimulation {
    private MissionControl control;
    private boolean running;

    public static void main(String[] args){
        RunSimulation sim = new RunSimulation();
        sim.displayMainMenu();
    }

    public RunSimulation(){
        this.control = new MissionControl(new TrackingSystem());
    }

    public void displayMainMenu(){}

    public void handleUserChoice(String choice){}
}
