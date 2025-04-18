package src;
import src.enums.Role;
public class UserFactory {

    public static User createUser(String username, String roleInput) {
        Role role = Role.fromInput(roleInput);

        if (role == null) {
            throw new IllegalArgumentException("Invalid role input: " + roleInput);
        }
                switch (role) {
            case SCIENTIST:
                return new Scientist(username);
            case SPACE_AGENCY_REPRESENTATIVE:
                return new SpaceAgencyRepresentative(username);
            case POLICYMAKER:
                return new PolicyMaker(username);
            case ADMIN:
                return new Admin(username);
            default:
                throw new IllegalArgumentException("Invalid role input: " + role);
        }
    }
}
