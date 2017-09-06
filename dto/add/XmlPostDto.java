package app.dto.add;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlPostDto {

    @XmlAttribute(name = "id")
    private String id;

    public XmlPostDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
