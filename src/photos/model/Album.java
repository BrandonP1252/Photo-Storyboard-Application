package photos.model;

import java.util.ArrayList;

public class Album {
    private ArrayList<Photo> photoList;

    public Album(String username) {
       photoList = new ArrayList<>();
    }
}
