package photos.model;

import java.util.ArrayList;

public class User {
    private String username;
    private ArrayList<Album> albumList;

    public User(String username) {
        this.username = username;
        albumList = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Album> getAlbumList() {
        return albumList;
    }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return username.equals(user.username);
    }
}
