package photos.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import photos.PhotosMain;

import java.io.IOException;
/**
 * Represents the controllers as enums.
 * @author Brandon Perez bkp48 
 * @author Julian Calle joc24
 */
public enum SceneType {
    /**
     * Represents log in controller.
     */
    LOGIN("/photos/resources/LogIn.fxml"),
    /**
     * Represents admin controller.
     */
    ADMIN("/photos/resources/Admin.fxml"),
    /**
     * Represents album controller.
     */
    ALBUMLIST("/photos/resources/AlbumList.fxml"),
    /**
     * Represents photo view controller.
     */
    PHOTOVIEW("/photos/resources/PhotoView.fxml"),
    /**
     * Represents slideshow controller.
     */
    SLIDESHOW("/photos/resources/Slideshow.fxml");

    /**
     * Represents scene as string.
     */
    private final String viewName;
    /**
     * Represents scene.
     */
    private Scene scene;

    /**
     * Loads given scene.
     * @param viewName Represents scene as string.
     */
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
 * finds the current scene and changes if needed
 * @return scene
 */
    public Scene getScene(){
        return scene;
    }
}
