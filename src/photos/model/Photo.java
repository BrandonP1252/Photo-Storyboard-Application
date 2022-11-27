package photos.model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Calendar;



public class Photo {
    private ArrayList<String> tags;
    private final Image image;
    private String caption;
    private Calendar calendar;
    private int month, day, year;
    private Tag tag;
    private ArrayList<Tag> newTags;


    public Photo(Image image) {
        this.image = image;
        calendar = Calendar.getInstance();
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DATE);
        year = calendar.get(Calendar.YEAR);
        calendar.set(Calendar.MILLISECOND,0);
        caption = "No caption set";
        tags = new ArrayList<>();
        newTags = new ArrayList<>();

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

    public Calendar getCalendar() {
        return calendar;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public ArrayList<Tag> getNewTags() {
        return newTags;
    }
}
