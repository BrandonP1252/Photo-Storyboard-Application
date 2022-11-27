package photos.model;

import javafx.scene.image.Image;

import java.util.Calendar;

import java.util.HashMap;

public class Photo {
    private String photoName;
    private HashMap<String, String> tags;
    private final Image image;
    private Image thumbnailImage;
    private String caption;
    private Calendar calendar;
    private int month, day, year;


    public Photo(Image image) {
        this.image = image;
        thumbnailImage = image;
        calendar = Calendar.getInstance();
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DATE);
        year = calendar.get(Calendar.YEAR);
        tags = new HashMap<>();
        caption = "No caption set";

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

    @Override
    public String toString() {
        return "Photo 1";
    }
}
