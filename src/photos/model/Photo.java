package photos.model;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;



public class Photo implements Serializable {
    private final Image image;
    private String caption;
    private ArrayList<Tag> tags;
    private String path;
    private Date date;

    public Photo(Image image, String path) {
        this.image = image;
        this.path = path;
        this.date = new Date();
        caption = "No caption set";
        tags = new ArrayList<>();
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Image getImage() {
        return image;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Photo)) {
            return false;
        }
        Photo photo = (Photo) obj;
        return path.equals(photo.getPath());
    }
    public Date getDate() {
        return date;
    }
}
