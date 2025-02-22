package com.example.project.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ApplicationUser {
    @Id
    @Column(name = "user_name")
    @JsonProperty("user_name")
    private String userName;

    @Column(name = "user_email")
    @JsonProperty("user_email")
    private String userEmail;

    @JsonProperty("password")
    private String password;

    @Column(name = "user_mobile")
    @JsonProperty("user_mobile")
    private String userMobile;

    @JsonProperty("location")
    private String location;

    public ApplicationUser(@JsonProperty("user_name") String userName,
                           @JsonProperty("user_email") String userEmail,
                           @JsonProperty("password") String password,
                           @JsonProperty("user_mobile") String userMobile,
                           @JsonProperty("location") String location) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
        this.userMobile = userMobile;
        this.location = location;
    }

    // Convenience constructor for signin
    public ApplicationUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
