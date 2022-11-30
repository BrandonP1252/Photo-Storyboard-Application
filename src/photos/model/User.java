package photos.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents the user object
 * @author Brandon Perez bkp49
 * @author Julian Calle joc24
 */
public class User implements Serializable {
    /**
     * Represents the username.
     */
    private String username;
    /**
     * Represents the album list.
     */
    private ArrayList<Album> albumList;

    /**
     * Initializes user
     * @param username Represents the username
     */
    public User(String username) {
        this.username = username;
        albumList = new ArrayList<>();
    }

    /**
     * Returns username.
     * @return username Returns username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns list of albums.
     * @return albumList returns album list.
     */
    public ArrayList<Album> getAlbumList() {
        return albumList;
    }

    /**
     * Returns string for user object.
     * @return username returns username.
     */
    @Override
    public String toString() {
        return username;
    }

    /**
     * Checks if object is equal with another object.
     * @param obj represents the object
     * @return boolean returns boolean whether username is equal with another username.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return username.equals(user.username);
    }
}
