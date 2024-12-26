package co.boldtec.salesgen.domain;

public class TeamMember {
    String name;
    String profileImgUrl;
    String role;

    // Constructor and getters
    public TeamMember(String name, String profileImgUrl, String role) {
        this.name = name;
        this.profileImgUrl = profileImgUrl;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getProfileImgUrl() {
        return profileImgUrl;
    }

    public String getRole() {
        return role;
    }
}
