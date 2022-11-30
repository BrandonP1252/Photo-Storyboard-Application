package photos.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import photos.PhotosMain;

import java.io.IOException;

public enum SceneType {
    LOGIN("/photos/resources/LogIn.fxml"),
    ADMIN("/photos/resources/Admin.fxml"),
    ALBUMLIST("/photos/resources/AlbumList.fxml"),
    PHOTOVIEW("/photos/resources/PhotoView.fxml"),
    SLIDESHOW("/photos/resources/Slideshow.fxml");


    private final String viewName;
    private Scene scene;
    SceneType(String viewName) {
        this.viewName = viewName;
        FXMLLoader fxmlLoader = new FXMLLoader(PhotosMain.class.getResource(viewName));
        try {
            this.scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/**
 * 
 * @return scene
 */

    public Scene getScene(){
        return scene;
    }
}
