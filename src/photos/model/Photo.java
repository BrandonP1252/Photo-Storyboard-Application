package photos.model;

import java.io.Serializable;
import java.util.ArrayList;



public class Photo implements Serializable {
    private String caption;
    private ArrayList<Tag> tags;
    private String path;
    private Date date;

    public Photo(String path) {
        this.path = path;
        this.date = new Date();
        caption = "No caption set";
        tags = new ArrayList<>();
    }
/**
 * 
 * @return
 */

    public String getCaption() {
        return caption;
    }
/**
 * 
 * @param caption
 */

    public void setCaption(String caption) {
        this.caption = caption;
    }
/**
 * 
 * @return displays tag
 */

    public ArrayList<Tag> getTags() {
        return tags;
    }
/**
 * 
 * @return path
 */

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
