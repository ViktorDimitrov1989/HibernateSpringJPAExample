package app.dto.add.wrappers;

import app.dto.add.AddCommentDto;
import app.models.Comment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "comments")
@XmlAccessorType(XmlAccessType.FIELD)
public class CommentsDtoWrapper {

    @XmlElement(name = "comment")
    private List<AddCommentDto> comments;

    public List<AddCommentDto> getComments() {
        return comments;
    }

    public void setComments(List<AddCommentDto> comments) {
        this.comments = comments;
    }
}
