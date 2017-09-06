package app.dto.xml;

import app.models.Post;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithPostCommentsView {
    @XmlElement(name = "username")
    private String username;
    @XmlElement(name = "most-comments")
    private Integer mostComments;

    public UserWithPostCommentsView() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getMostComments() {
        return mostComments;
    }

    public void setMostComments(Integer mostComments) {
        this.mostComments = mostComments;
    }

}
