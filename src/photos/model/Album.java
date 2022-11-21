package photos.model;

import java.util.ArrayList;

public class Album {
    private ArrayList<Photo> photoList;
    private String albumName;

    public Album(String albumName) {
        this.albumName = albumName;
        photoList = new ArrayList<>();
    }
}
