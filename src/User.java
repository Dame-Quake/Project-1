package src;

import java.util.Scanner;

public class User {
    protected String username;
    private String role;

    public User(String username, String role){
        this.username = username;
        this.role = role;
    }

    public String getName() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public static User identifyUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your role \n1: Scientist\n2: Space Agency Representative\n3: Policymaker\n4: Admin\nEnter: ");
        String role = scanner.nextLine();

        switch (role) {
            //implementation for the rest of the roles, we'll do later in the project

            case "1":
                return new Scientist(name);
            case "2":
                return new SpaceAgencyRepresentative(name);
            case "3":
                return new PolicyMaker(name);
            case "4":
                return new Admin(name);
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
                
        }
    }
}


