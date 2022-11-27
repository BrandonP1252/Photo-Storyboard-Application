package photos.model;

import java.util.ArrayList;

public class Album {
    private ArrayList<Photo> photoList;
    private String albumName;
    private String dateRange;
    private int numberOfPhotos;

    public Album(String albumName) {
        this.albumName = albumName;
        photoList = new ArrayList<>();
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String newAlbumName) {
        this.albumName = newAlbumName;
    }

    @Override
    public String toString() {
        numberOfPhotos = photoList.size();
        return albumName + "\t\tPhotos: " + numberOfPhotos + "\t\tDate Range: " + getDateRange();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Album)) {
            return false;
        }
        Album album = (Album) obj;
        return albumName.equals(album.albumName);
    }

    public ArrayList<Photo> getPhotoList() {
        return photoList;
    }
    public String getDateRange() {
        if (photoList == null || photoList.isEmpty()) {
            return "";
        }
        Photo min = photoList.get(0);
        Photo max = photoList.get(0);
        for (Photo photo : photoList) {
            if (photo.getCalendar().compareTo(min.getCalendar()) < 0) {
                min = photo;
            }
        }
        for (Photo photo : photoList) {
            if (photo.getCalendar().compareTo(max.getCalendar()) > 0) {
                max = photo;
            }
        }
        String earliest = min.getMonth() + "/" + min.getDay() + "/" + min.getYear();
        String latest = max.getMonth() + "/" + max.getDay() + "/" + max.getYear();
        return earliest + " - " + latest;

    }
}
