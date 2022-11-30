package photos.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Album implements Serializable {
    private ArrayList<Photo> photoList;
    private String albumName;
    private int numberOfPhotos;

    public Album(String albumName) {
        this.albumName = albumName;
        photoList = new ArrayList<>();
    }

    public Album() {
        photoList = new ArrayList<>();
    }
/**
 * 
 * @return albumName
 */

    public String getAlbumName() {
        return albumName;
    }
/**
 * 
 * @param newAlbumName
 */

    public void setAlbumName(String newAlbumName) {
        this.albumName = newAlbumName;
    }

    @Override
    public String toString() {
        numberOfPhotos = photoList.size();
        return albumName + "\t\tPhotos: " + numberOfPhotos + "\t\tDate Range: " +
                (numberOfPhotos > 0 ? getDateRange() : "");
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Album)) {
            return false;
        }
        Album album = (Album) obj;
        return albumName.equals(album.albumName);
    }
/**
 * 
 * @return PhotoList
 */

    public ArrayList<Photo> getPhotoList() {
        return photoList;
    }
/**
 * 
 * @return photolist date
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
