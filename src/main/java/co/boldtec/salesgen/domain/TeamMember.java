package co.boldtec.salesgen.domain;

import lombok.Data;

@Data
public class TeamMember {
    String name;
    String profileImgUrl;
    String role;

    public TeamMember(String name, String profileImgUrl, String role) {
        this.name = name;
        this.profileImgUrl = profileImgUrl;
        this.role = role;
    }
}
