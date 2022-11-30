package photos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import photos.PhotosMain;
import photos.model.Album;
import photos.model.Photo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author Brandon Perez bkp48 
 * @author Julian Calle joc24
 */

public class SlideshowController {
    @FXML
    private ImageView imageView;
    private ArrayList<Photo> photoList = new ArrayList<>();
    private int index;
    @FXML
    private void onNext() throws FileNotFoundException {
        if (index+1 < photoList.size()) {
            setIndex(index+1);
            Image image = PhotoViewController.loadImage(photoList.get(index).getPath());
            imageView.setImage(image);
        }
    }

    @FXML
    private void onPrevious() throws FileNotFoundException {
        if (index-1 >= 0) {
            setIndex(index-1);
            Image image = PhotoViewController.loadImage(photoList.get(index).getPath());
            imageView.setImage(image);
        }
    }
    @FXML
    private void onBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PhotosMain.class.getResource("/photos/resources/PhotoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        PhotoViewController controller = fxmlLoader.getController();
        // Load photo list
        ObservableList<Photo> photoList = FXCollections.observableList(AlbumListController.getCurrentAlbum().getPhotoList());
        controller.setPhotoList(photoList);

        ObservableList<Album> albumList = FXCollections.observableArrayList(LogInController.getCurrentUser().getAlbumList());
        controller.setAlbumList(albumList);
        PhotosMain.getStage().setScene(scene);
    }

    @FXML
    private void onLogOut() {
        PhotosMain.switchScene(SceneType.LOGIN);
    }
/**
 * shows the current list of photos.
 * @return photoList
 */

    public ArrayList<Photo> getPhotoList() {
        return photoList;
    }
/**
 * changes the view of the image.
 * @return imageView
 */

    public ImageView getImageView() {
        return imageView;
    }
/**
 * sets the current index of slideshow.
 * @param index
 */
    public void setIndex(int index) {
        this.index = index;
    }
/**
 * finds the current index of slideshow.
 * @return index
 */
    public int getIndex() {
        return index;
    }
}
