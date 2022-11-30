package photos.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 * Represents album object.
 * @author Brandon Perez bkp48 
 * @author Julian Calle joc24
 */

public class Album implements Serializable {
    /**
     * Represents list of photos.
     */
    private ArrayList<Photo> photoList;
    /**
     * Represents album name.
     */
    private String albumName;
    /**
     * Represents number of photos.
     */
    private int numberOfPhotos;

    /**
     * Initializes album.
     * @param albumName Represents album name.
     */
    public Album(String albumName) {
        this.albumName = albumName;
        photoList = new ArrayList<>();
    }

    /**
     * Initializes no arguments album.
     */
    public Album() {
        photoList = new ArrayList<>();
    }

/**
 * Gets album name.
 * @return albumname Represents name of the album
 */
    public String getAlbumName() {
        return albumName;
    }

    /**
     * Sets album name
     * @param newAlbumName Represents new album name.
     */
    public void setAlbumName(String newAlbumName) {
        this.albumName = newAlbumName;
    }

    /**
     * ToString method for album.
     * @return String Returns string for album.
     */
    @Override
    public String toString() {
        numberOfPhotos = photoList.size();
        return albumName + "\t\tPhotos: " + numberOfPhotos + "\t\tDate Range: " +
                (numberOfPhotos > 0 ? getDateRange() : "");
    }

    /**
     * Checks if albums are equal.
     * @param obj Represents album object.
     * @return boolean Returns whether album is equal or not.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Album)) {
            return false;
        }
        Album album = (Album) obj;
        return albumName.equals(album.albumName);
    }
/**
 * Gets list of photos.
 * @return photoList Represents list of photos.
 */

    public ArrayList<Photo> getPhotoList() {
        return photoList;
    }

    /**
     * Gets range of date for album.
     * @return String Returns date range as string.
     */
    public String getDateRange() {

        LocalDate min = photoList.get(0).getDate().getLocalDate();
        LocalDate max = photoList.get(0).getDate().getLocalDate();

        for (Photo photo : photoList) {
            if (min.compareTo(photo.getDate().getLocalDate()) > 0) {
                min = photo.getDate().getLocalDate();
            }

            if (max.compareTo(photo.getDate().getLocalDate()) < 0) {
                max = photo.getDate().getLocalDate();
            }
        }

        return min.toString() + " to " + max.toString();

    }
}
