package app.dto.view.json;

import app.models.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Set;

public class PopularUserView {
    @Expose
    @SerializedName(value = "user")
    private String username;
    @Expose
    @SerializedName(value = "followers")
    private Integer followersCount;
    private Set<User> followers;

    public PopularUserView() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }
}
