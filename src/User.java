package src;

import java.util.Scanner;

public class User {
    protected String username;
    private String role;
    private static String savedUsername = null;

    public User(String username, String role){
        this.username = username;
        this.role = role;
    }

    public String getName() {
        return username ;
    }

    public String getRole() {
        return role;
    }

    public static String[] promptForRoleInput(){
        Scanner scanner = new Scanner(System.in);
        if(savedUsername == null||savedUsername.isBlank()){ 
            while(true){
                System.out.println("____________________________________");
                System.out.println("Enter your name or enter (x) to exit the program ");
                savedUsername = scanner.nextLine();
                if (savedUsername.equalsIgnoreCase("x")) {
                    return new String[]{"x",""};
                }
                if (savedUsername.isBlank()) {
                    System.out.println("Username cannot be blank. Please try again.");
                } 
                else {
                    break; // valid name entered
                }
            }
        }

        System.out.print("Enter your role. Username: "+ savedUsername +//
        " \n1: Scientist\n" +
            "2: Space Agency Representative\n" +
            "3: Policymaker\n" +
            "4: Admin\n" +
            "(x to exit simulation)\n" +
            "Enter: ");
        
        String roleInput = scanner.nextLine();

        if (roleInput.equalsIgnoreCase("x")) {
            return new String[]{"x", ""};
        }

        return new String[]{savedUsername, roleInput};
        
    }
}


