package photos;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import photos.controller.SceneType;
import photos.model.Album;
import photos.model.Photo;
import photos.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PhotosMain extends Application {

    private static ArrayList<User> userList;
    private static Stage stage;
    private static int count = 0;
    @Override
    public void start(Stage stage) throws IOException {
        PhotosMain.stage = stage;
        userList = new ArrayList<>();
        Parent root = FXMLLoader.load(getClass().getResource("/photos/resources/LogIn.fxml"));
        createStock(new User("stock"));
        stage.setResizable(false);
        stage.setTitle("Photo Storyboard Application");
        Scene start = new Scene(root);
        stage.setScene(start);
        stage.show();
    }
    private void createStock(User user) throws FileNotFoundException {
        userList.add(user);
        user.getAlbumList().add(new Album("stock"));
        Album album = user.getAlbumList().get(0);

        String path1 = "src/stock/EDP.jpg";
        InputStream fileInputStream1 = new FileInputStream(path1);
        Image image1 = new Image(fileInputStream1);
        Photo photo1 = new Photo(image1, path1);
        album.getPhotoList().add(photo1);

        String path2 = "src/stock/Pepperoni.png";
        InputStream fileInputStream2 = new FileInputStream(path2);
        Image image2 = new Image(fileInputStream2);
        Photo photo2 = new Photo(image2, path2);
        album.getPhotoList().add(photo2);

        String path3 = "src/stock/Hasbullah.png";
        InputStream fileInputStream3 = new FileInputStream(path3);
        Image image3 = new Image(fileInputStream3);
        Photo photo3 = new Photo(image3, path3);
        album.getPhotoList().add(photo3);

        String path4 = "src/stock/Java.png";
        InputStream fileInputStream4 = new FileInputStream(path4);
        Image image4 = new Image(fileInputStream4);
        Photo photo4 = new Photo(image4, path4);
        album.getPhotoList().add(photo4);

        String path5 = "src/stock/Rutgers.jpg";
        InputStream fileInputStream5 = new FileInputStream(path5);
        Image image5 = new Image(fileInputStream5);
        Photo photo5 = new Photo(image5, path5);
        album.getPhotoList().add(photo5);
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

    public static int getCount() {
        count++;
        return count;
    }

    public static void main(String[] args) {
        launch();
    }
}
