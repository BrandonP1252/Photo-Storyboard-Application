package photos.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import photos.PhotosMain;
import photos.model.User;

public class LogInController {
    @FXML
    private TextField logInText;

    @FXML
    private void onLogIn() {
        String username = logInText.getText();

        for (User user: PhotosMain.getUserList()) {
            String cmpUsername = user.getUsername();
            if (username.equals(cmpUsername) && !username.equals("admin")) {
                PhotosMain.switchScene(SceneType.ALBUMLIST);

            }
        }
        if (username.equals("admin")) {
            PhotosMain.switchScene(SceneType.ADMIN);
        }

    }



}
