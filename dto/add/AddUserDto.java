package app.dto.add;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddUserDto {
    @Expose
    private String username;
    @Expose
    private String password;
    @Expose
    @SerializedName("profile_picture")
    private String profilePicture;

    public AddUserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
