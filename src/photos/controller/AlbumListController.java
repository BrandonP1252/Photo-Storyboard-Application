package photos.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import photos.PhotosMain;
import photos.model.Album;
import photos.model.User;

public class AlbumListController {
    @FXML
    private ListView<Album> albumList;

    @FXML
    private TextField userInput;

    @FXML
    private void onCreateAlbum() {
        if (userInput.getText().isBlank()) {
            return;
        }
        String albumName = userInput.getText();
        Album album = new Album(albumName);
    }

    @FXML
    private void onRename() {

    }

    @FXML
    private void onDelete() {

    }

    @FXML
    private void onOpen() {

    }

    @FXML
    private void onLogOut() {
        PhotosMain.switchScene(SceneType.LOGIN);
    }


}
