package photos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import photos.PhotosMain;
import photos.model.Album;
import photos.model.Photo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PhotoViewController {

    @FXML
    private ImageView imageView;
    @FXML
    private TextField captionText;

    @FXML
    private TextField dateText;

    @FXML
    private ListView<Photo> photoList;

    @FXML
    private TextField userInputPhoto;

    @FXML
    private TextField userInputSearch;

    @FXML
    private ListView<Album> albumList;

    @FXML
    private void onSlideshow() {
        PhotosMain.switchScene(SceneType.SLIDESHOW);
    }

    @FXML
    private void onAddPhoto() {
        if (userInputPhoto.getText().isBlank()) {
            return;
        }
        try {
            String fileLocation = userInputPhoto.getText();
            InputStream stream = new FileInputStream(fileLocation);
            Image image = new Image(stream);
            Photo photo = new Photo(image);
            AlbumListController.getCurrentAlbum().getPhotoList().add(photo);
            photoList.setItems(FXCollections.observableArrayList(AlbumListController.getCurrentAlbum().getPhotoList()));
            photoList.setCellFactory(photoListView -> new ImageStringView());
            userInputPhoto.setText("");
        } catch (IOException e) {
            // ADD ALERT
            System.out.println("Invalid file path");
        }
    }
    @FXML
    private void onRemovePhoto() {
        Photo photo = photoList.getSelectionModel().getSelectedItem();
        Album album = AlbumListController.getCurrentAlbum();
        album.getPhotoList().remove(photo);
        photoList.setItems(FXCollections.observableArrayList(album.getPhotoList()));
        imageView.setImage(null);
    }

    @FXML
    private void onPhotoListMouseClicked() {
        imageView.setImage(photoList.getSelectionModel().getSelectedItem().getImage());
    }

    @FXML
    private void onCaptionOrRecaption() {
        Photo photo = photoList.getSelectionModel().getSelectedItem();
        photo.setCaption(userInputPhoto.getText());
        photoList.setItems(FXCollections.observableArrayList(AlbumListController.getCurrentAlbum().getPhotoList()));
        userInputPhoto.setText("");
    }

    static class ImageStringView extends ListCell<Photo> {
        @Override
        public void updateItem(Photo item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null) {
                setGraphic(null);
                setText(null);
            } else {
                ImageView thumbnail = new ImageView();
                thumbnail.setFitHeight(32);
                thumbnail.setFitWidth(32);
                thumbnail.setImage(item.getImage());
                setGraphic(thumbnail);
                setText(item.getCaption());
            }
        }
    }

    public void setPhotoList(ObservableList<Photo> newList) {
        photoList.setItems(newList);

    }



}
