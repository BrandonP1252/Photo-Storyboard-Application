/**
 * Represents the controller classes.
 */
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
/**
 * Represents log in controller.
 * @author Brandon Perez bkp48 
 * @author Julian Calle joc24
 */
public class LogInController {
/**
* Represents the user
*/
    private static User currentUser;
/**
* represents the text shown on log in
*/
    @FXML
    private TextField logInText;
/**
 * When log in it will load the listview for the user.
 * @throws IOException
 */
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
    /**
     * loads the user of the past session and finds the list view
     * @param currentUser
     * @throws IOException
     */

    // Updates list view and switches scene
    private void loadListView(User currentUser) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PhotosMain.class.getResource("/photos/resources/AlbumList.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AlbumListController controller = fxmlLoader.getController();
        ObservableList<Album> newList = FXCollections.observableArrayList(currentUser.getAlbumList());
        controller.setAlbumList(newList);
        PhotosMain.getStage().setScene(scene);
    }
    /**
     * Finds the current user and finds current session
     * @return user
     */
    public static User getCurrentUser() {
        return currentUser;
    }



}
