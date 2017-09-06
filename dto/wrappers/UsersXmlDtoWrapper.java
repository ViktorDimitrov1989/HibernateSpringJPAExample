package app.dto.wrappers;

import app.dto.xml.UserWithPostCommentsView;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "users")
public class UsersXmlDtoWrapper {

    @XmlElement(name = "user")
    private List<UserWithPostCommentsView> users;

    public UsersXmlDtoWrapper() {
    }

    public List<UserWithPostCommentsView> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithPostCommentsView> users) {
        this.users = users;
    }
}
