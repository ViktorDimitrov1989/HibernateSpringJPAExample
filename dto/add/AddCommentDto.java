package app.dto.add;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "comment")
public class AddCommentDto {

    @XmlElement(name = "content")
    private String content;
    @XmlElement(name = "user")
    private String userName;
    @XmlElement(name = "post")
    private XmlPostDto postDto;

    public AddCommentDto() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public XmlPostDto getPostDto() {
        return postDto;
    }

    public void setPostDto(XmlPostDto postDto) {
        this.postDto = postDto;
    }
}
