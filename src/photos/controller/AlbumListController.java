package photos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import photos.PhotosMain;
import photos.model.Album;
import photos.model.User;



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
    private void onOpen() {
        currentAlbum = albumList.getSelectionModel().getSelectedItem();
        PhotosMain.switchScene(SceneType.PHOTOVIEW);
    }

    @FXML
    private void onLogOut() {
        PhotosMain.switchScene(SceneType.LOGIN);
    }

    public static Album getCurrentAlbum() {
        return currentAlbum;
    }

    public void setAlbumList(ObservableList<Album> newList) {
        albumList.setItems(newList);
    }


}
