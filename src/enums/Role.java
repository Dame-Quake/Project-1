package src.enums;

public enum Role {
    SCIENTIST,
    SPACE_AGENCY_REPRESENTATIVE,
    POLICYMAKER,
    ADMIN;

    public static Role fromInput(String input) {
        switch (input.trim()) {
            case "1":
            case "SCIENTIST":
                return SCIENTIST;
            case"2":
            case "SPACE_AGENCY_REPRESENTATIVE":
                return SPACE_AGENCY_REPRESENTATIVE;
            case "3":
            case "POLICYMAKER":
                return POLICYMAKER;
            case "4":
            case "ADMIN":
                return ADMIN;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case SCIENTIST:
                return "Scientist";
            case SPACE_AGENCY_REPRESENTATIVE:
                return "Space Agency Representative";
            case POLICYMAKER:
                return "Policymaker";
            case ADMIN:
                return "Admin";
            default:
                return "Unknown Role";
        }
    }
    
}
