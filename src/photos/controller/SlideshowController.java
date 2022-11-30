package photos.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import photos.PhotosMain;
import photos.model.Photo;

import java.io.FileNotFoundException;
import java.util.ArrayList;

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
    private void onLogOut() {
        PhotosMain.switchScene(SceneType.LOGIN);
    }
/**
 * 
 * @return photoList
 */

    public ArrayList<Photo> getPhotoList() {
        return photoList;
    }
/**
 * 
 * @return imageView
 */

    public ImageView getImageView() {
        return imageView;
    }
/**
 * 
 * @param index
 */
    public void setIndex(int index) {
        this.index = index;
    }
/**
 * 
 * @return index
 */
    public int getIndex() {
        return index;
    }
}
