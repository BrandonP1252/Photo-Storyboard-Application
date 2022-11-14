package photos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import photos.PhotosMain;

import java.io.IOException;

public class LogInController {
    @FXML
    private TextField logInText;

    @FXML
    private void onLogIn(ActionEvent event) throws IOException {
        String user = logInText.getText();

        if (user.equals("admin")) {
            PhotosMain.switchScene(SceneType.ADMIN);
        }




    }



}
