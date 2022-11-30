package photos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import photos.PhotosMain;
import photos.model.User;

import java.io.IOException;
/**
 * 
 * @author Brandon Perez bkp48 
 * @author Julian Calle joc24
 */
public class AdminController {

    @FXML
    private ListView<User> userList;

    @FXML
    private TextField userInput;

    @FXML
    private void onAddUser() throws IOException {
        if (userInput.getText().isBlank()) {
            return;
        }
        User user = new User(userInput.getText());
        if (!PhotosMain.getUserList().contains(user)) {
            PhotosMain.getUserList().add(user);
        }
        userList.setItems(FXCollections.observableArrayList(PhotosMain.getUserList()));
        userInput.setText("");
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
/**
 * Finds the user and creates a newlist for them.
 * @param newList 
 */
    public void setUserList(ObservableList<User> newList) {
        userList.setItems(newList);
    }

}
