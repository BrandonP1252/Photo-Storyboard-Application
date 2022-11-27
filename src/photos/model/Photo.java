package photos.model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.HashMap;
import java.util.Map;

public class Photo {
    private String photoName;
    private ArrayList<String> tags;
    private final Image image;
    private String caption;
    private Calendar calendar;
    private int month, day, year;


    public Photo(Image image) {
        this.image = image;
        calendar = Calendar.getInstance();
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DATE);
        year = calendar.get(Calendar.YEAR);
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
    public String toStringDate() {
        return month + "/" + day + "/" + year;
    }
    public ArrayList<String> getTags() {
        return tags;
    }
}
