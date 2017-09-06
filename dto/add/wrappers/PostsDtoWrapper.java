package app.dto.add.wrappers;

import app.dto.add.AddPostDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "posts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostsDtoWrapper {

    @XmlElement(name = "post")
    private List<AddPostDto> posts;

    public PostsDtoWrapper() {
    }

    public List<AddPostDto> getPosts() {
        return posts;
    }

    public void setPosts(List<AddPostDto> posts) {
        this.posts = posts;
    }
}
