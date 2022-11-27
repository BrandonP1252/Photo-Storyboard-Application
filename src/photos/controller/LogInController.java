package photos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import photos.PhotosMain;
import photos.model.Album;
import photos.model.User;

import java.io.IOException;

public class LogInController {
    private static User currentUser;
    @FXML
    private TextField logInText;

    @FXML
    private void onLogIn() throws IOException {
        String username = logInText.getText();

        if (username.equals("admin")) {
            FXMLLoader fxmlLoader = new FXMLLoader(PhotosMain.class.getResource("/photos/resources/Admin.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            AdminController controller = fxmlLoader.getController();
            ObservableList<User> newList = FXCollections.observableList(PhotosMain.getUserList());
            controller.setUserList(newList);
            PhotosMain.getStage().setScene(scene);
        }

        for (User user: PhotosMain.getUserList()) {
            String cmpUsername = user.getUsername();
            if (username.equals(cmpUsername) && !username.equals("admin")) {
                currentUser = user;
                loadListView(currentUser);
            }
        }

        logInText.setText("");
    }

    // Updates list view and switches scene
    private void loadListView(User currentUser) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PhotosMain.class.getResource("/photos/resources/AlbumList.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AlbumListController controller = fxmlLoader.getController();
        ObservableList<Album> newList = FXCollections.observableArrayList(currentUser.getAlbumList());
        controller.setAlbumList(newList);
        PhotosMain.getStage().setScene(scene);
    }
    public static User getCurrentUser() {
        return currentUser;
    }



}
