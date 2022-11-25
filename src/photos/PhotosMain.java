package photos;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import photos.controller.SceneType;
import photos.model.Album;
import photos.model.User;

import java.io.IOException;
import java.util.ArrayList;

public class PhotosMain extends Application {

    private static ArrayList<User> userList;
    private static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        PhotosMain.stage = stage;
        userList = new ArrayList<>();
        Parent root = FXMLLoader.load(getClass().getResource("/photos/resources/LogIn.fxml"));
        stage.setResizable(false);
        stage.setTitle("Photo Storyboard Application");
        Scene start = new Scene(root);
        stage.setScene(start);
        stage.show();
    }

    public static void switchScene (SceneType sceneType) {
        stage.setScene(sceneType.getScene());
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch();
    }
}
