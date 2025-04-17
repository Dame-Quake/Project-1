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

        System.out.print("Enter your role (Scientist, SpaceAgency, Policymaker): ");
        String role = scanner.nextLine();
        return new User(name, role);

        // switch (role) {
        //     //implementation for the rest of the roles, we'll do later in the project

        //     case "Scientist":
        //         return new Scientist(name);
        //     // case "SpaceAgency":
        //     //     return new SpaceAgency(name);
        //     // case "Policymaker":
        //     //     return new Policymaker(name);
        //     default:
        //         throw new IllegalArgumentException("Invalid role: " + role);
        // }
    }
}


