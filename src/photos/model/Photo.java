package photos.model;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Represents the photo.
 * @author Brandon Perez bkp49
 * @author Julian Calle joc24
 */
public class Photo implements Serializable {
    /**
     * Represents the caption of photo.
     */
    private String caption;
    /**
     * Represents tags of the photo.
     */
    private ArrayList<Tag> tags;
    /**
     * Represents the path of the photo.
     */
    private String path;
    /**
     * Represents the date of the photo.
     */
    private Date date;

    /**
     * Initializes photo
     * @param path Represents path of photo.
     */
    public Photo(String path) {
        this.path = path;
        this.date = new Date();
        caption = "No caption set";
        tags = new ArrayList<>();
    }

    /**
     * Returns caption of photo.
     * @return caption Represents caption of photo.
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Sets caption of photo.
     * @param caption Represents caption of photo.
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * Returns list of tags of photo.
     * @return tags Represents list of tags.
     */
    public ArrayList<Tag> getTags() {
        return tags;
    }

    /**
     * Returns path of photo
     * @return path Represents path of photo.
     */
    public String getPath() {
        return path;
    }

    /**
     * Checks if two photo objects are equal.
     * @param obj Represents the photo object.
     * @return boolean Returns whether photo is equal or not.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Photo)) {
            return false;
        }
        Photo photo = (Photo) obj;
        return path.equals(photo.getPath());
    }

    /**
     * Returns date of photo
     * @return date Represents the date of photo.
     */
    public Date getDate() {
        return date;
    }
}
