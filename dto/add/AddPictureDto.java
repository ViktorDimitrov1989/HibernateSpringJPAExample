package app.dto.add;

import com.google.gson.annotations.Expose;

public class AddPictureDto {
    @Expose
    private String path;
    @Expose
    private Double size;

    public AddPictureDto() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }
}
