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
/**
 * Represents Album controller.
 * @author Brandon Perez bkp48 
 * @author Julian Calle joc24
 */

public class AlbumListController {
/**
	 * Represents the current album.
	 */
    private static Album currentAlbum;
     /**
     * represents the album list.
     */
    @FXML
    private ListView<Album> albumList;
/**
 * represents the input of the user.
 */
    @FXML
    private TextField userInput;
/**
 *  creates a new album for the user.
 */
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
/**
 * renames the album of the user.
 */
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
/**
 * deletes the selected album.
 */
    @FXML
    private void onDelete() {
        Album album = albumList.getSelectionModel().getSelectedItem();
        User user = LogInController.getCurrentUser();
        user.getAlbumList().remove(album);
        albumList.setItems(FXCollections.observableArrayList(user.getAlbumList()));
    }
/**
 * opens the selected album.
 * @throws IOException
 */
    @FXML
    private void onOpen() throws IOException {
        currentAlbum = albumList.getSelectionModel().getSelectedItem();
        if (currentAlbum == null) return;
        loadPhotoView(currentAlbum);
    }
/**
 * logs out the user when the button is pressed.
 */
    @FXML
    private void onLogOut() {
        PhotosMain.switchScene(SceneType.LOGIN);
    }
/**
 * When chosen the album will be displayed.
 * @return currentAlbum
 */
    public static Album getCurrentAlbum() {
        return currentAlbum;
    }
/**
 * A newList is created for the user in Album
 * @param newList
 */

    public void setAlbumList(ObservableList<Album> newList) {
        albumList.setItems(newList);
    }
/**
 * Finds the selected Album from the past session and displays.
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
