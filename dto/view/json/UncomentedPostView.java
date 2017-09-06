package app.dto.view.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UncomentedPostView {

    @Expose
    private Integer id;
    @Expose
    @SerializedName(value = "picture")
    private String picturePath;
    @Expose
    @SerializedName(value = "user")
    private String userUsername;

    public UncomentedPostView() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }
}
