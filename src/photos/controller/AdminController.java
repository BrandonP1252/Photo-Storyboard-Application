package photos.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import photos.PhotosMain;
import photos.model.User;

public class AdminController {

    @FXML
    private ListView<User> userList;

    @FXML
    private TextField userInput;

    @FXML
    private void onAddUser() {
        if (userInput.getText().isBlank()) {
            return;
        }
        User user = new User(userInput.getText());
        if (!PhotosMain.getUserList().contains(user)) {
            PhotosMain.getUserList().add(user);
        }
        userList.setItems(FXCollections.observableArrayList(PhotosMain.getUserList()));
    }

    @FXML
    private void onDeleteUser() {
        User user = userList.getSelectionModel().getSelectedItem();
        PhotosMain.getUserList().remove(user);
        userList.setItems(FXCollections.observableArrayList(PhotosMain.getUserList()));
    }

    @FXML
    private void onLogOut() {
        PhotosMain.switchScene(SceneType.LOGIN);
    }



}
