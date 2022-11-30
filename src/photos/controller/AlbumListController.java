package photos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import photos.PhotosMain;
import photos.model.Album;
import photos.model.Photo;
import photos.model.User;

import java.io.IOException;


public class AlbumListController {
    private static Album currentAlbum;
    @FXML
    private ListView<Album> albumList;

    @FXML
    private TextField userInput;

    @FXML
    private void onCreateAlbum() {

        if (userInput.getText().isBlank()) {
            return;
        };

        User user = LogInController.getCurrentUser();

        if (userInput.getText().isBlank()) {
            return;
        };

        Album album = new Album(userInput.getText());
        if (!user.getAlbumList().contains(album)) {
            user.getAlbumList().add(album);
        }

        albumList.setItems(FXCollections.observableArrayList(user.getAlbumList()));
        userInput.setText("");
    }

    @FXML
    private void onRename() {
        User user = LogInController.getCurrentUser();
        if (userInput.getText().isBlank()) {
            return;
        }

        for(Album cmp : user.getAlbumList()) {
            if (cmp.getAlbumName().equals(userInput.getText())) {
                return;
            }
        }

        for (int i = 0; i < user.getAlbumList().size(); i++) {
            if (user.getAlbumList().get(i).equals(albumList.getSelectionModel().getSelectedItem())) {
                user.getAlbumList().get(i).setAlbumName(userInput.getText());
            }
        }
        albumList.setItems(FXCollections.observableArrayList(user.getAlbumList()));
        userInput.setText("");
    }

    @FXML
    private void onDelete() {
        Album album = albumList.getSelectionModel().getSelectedItem();
        User user = LogInController.getCurrentUser();
        user.getAlbumList().remove(album);
        albumList.setItems(FXCollections.observableArrayList(user.getAlbumList()));
    }

    @FXML
    private void onOpen() throws IOException {
        currentAlbum = albumList.getSelectionModel().getSelectedItem();
        if (currentAlbum == null) return;
        loadPhotoView(currentAlbum);
    }

    @FXML
    private void onLogOut() {
        PhotosMain.switchScene(SceneType.LOGIN);
    }
/**
 * 
 * @return current Album
 */
    public static Album getCurrentAlbum() {
        return currentAlbum;
    }
/**
 * 
 * @param newList
 */

    public void setAlbumList(ObservableList<Album> newList) {
        albumList.setItems(newList);
    }
/**
 * 
 * @param currentAlbum
 * @throws IOException
 */

    private void loadPhotoView(Album currentAlbum) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PhotosMain.class.getResource("/photos/resources/PhotoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        PhotoViewController controller = fxmlLoader.getController();
        // Load photo list
        ObservableList<Photo> photoList = FXCollections.observableList(currentAlbum.getPhotoList());
        controller.setPhotoList(photoList);

        // Load album list
        ObservableList<Album> albumList = FXCollections.observableArrayList(LogInController.getCurrentUser().getAlbumList());
        controller.setAlbumList(albumList);
        PhotosMain.getStage().setScene(scene);
    }

}
