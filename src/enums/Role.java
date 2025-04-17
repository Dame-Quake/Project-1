package src.enums;

public enum Role {
    SCIENTIST,
    SPACE_AGENCY_REPRESENTATIVE,
    POLICYMAKER,
    ADMIN;

    public static Role fromString(String role) {
        switch (role.toUpperCase()) {
            case "SCIENTIST":
                return SCIENTIST;
            case "SPACE_AGENCY_REPRESENTATIVE":
                return SPACE_AGENCY_REPRESENTATIVE;
            case "POLICYMAKER":
                return POLICYMAKER;
            case "ADMIN":
                return ADMIN;
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }
    
}
