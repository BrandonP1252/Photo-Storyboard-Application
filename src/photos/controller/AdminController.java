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
 * Represents admin controller.
 * @author Brandon Perez bkp48 
 * @author Julian Calle joc24
 */
public class AdminController {
/**
 * Calls for a new user
 */
    @FXML
    private ListView<User> userList;
/**
 * Allows for the user to input text
 */
    @FXML
    private TextField userInput;
/**
 * Adds a new user to the List.
 */   
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
/**
 * Deletes the selected user from the user List.
 */
    @FXML
    private void onDeleteUser() {
        User user = userList.getSelectionModel().getSelectedItem();
        PhotosMain.getUserList().remove(user);
        userList.setItems(FXCollections.observableArrayList(PhotosMain.getUserList()));

    }
/**
 * Changes the scene from the login screen.
 */
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
