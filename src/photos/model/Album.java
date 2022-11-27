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
        return albumName + "\t-Number of Photos: " + numberOfPhotos + "\t-Date Range:" ;
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
}
