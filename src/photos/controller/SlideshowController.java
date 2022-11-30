package photos.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import photos.PhotosMain;
import photos.model.Photo;

import java.util.ArrayList;

public class SlideshowController {
    @FXML
    private ImageView imageView;
    private ArrayList<Photo> photoList = new ArrayList<>();
    private int index;
    @FXML
    private void onNext() {
        if (index+1 < photoList.size()) {
            setIndex(index+1);
            imageView.setImage(photoList.get(index).getImage());
        }
    }

    @FXML
    private void onPrevious() {
        if (index-1 >= 0) {
            setIndex(index-1);
            imageView.setImage(photoList.get(index).getImage());
        }
    }

    @FXML
    private void onLogOut() {
        PhotosMain.switchScene(SceneType.LOGIN);
    }

    public ArrayList<Photo> getPhotoList() {
        return photoList;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
