package photos;

import javafx.application.Application;
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

public class PhotosMain extends Application {

    private static ArrayList<User> userList;
    private static Stage stage;
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        PhotosMain.stage = stage;
        userList = new ArrayList<>();
        Parent root = FXMLLoader.load(getClass().getResource("/photos/resources/LogIn.fxml"));
        deserializeUsers();
        stage.setResizable(false);
        stage.setTitle("Photo Storyboard Application");
        Scene start = new Scene(root);
        stage.setScene(start);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        File dir = new File("src/data/users");
        for (File file : dir.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }

        for (User user : userList) {
            String fileOutput = "src/data/users/" + user.getUsername() + ".ser";
            FileOutputStream fileOut = new FileOutputStream(fileOutput);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(user);
            out.close();
            fileOut.close();
        }
    }
    
/**
 * 
 * @throws IOException
 * @throws ClassNotFoundException
 */
    public static void deserializeUsers() throws IOException, ClassNotFoundException {
        File path = new File("src/data/users");
        File[] files = path.listFiles();
        for (File file : files) {
            User user;
            String fileName = file.getName();
            FileInputStream fileIn = new FileInputStream("src/data/users/"+fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            user = (User) in.readObject();
            userList.add(user);
            in.close();
            fileIn.close();
        }
    }
/**
 * 
 * @param sceneType changes scene
 */

    public static void switchScene (SceneType sceneType) {
        stage.setScene(sceneType.getScene());
    }
/**
 * 
 * @return user profile
 */

    public static ArrayList<User> getUserList() {
        return userList;
    }
/**
* 
* @return  selected stage
*/
    public static Stage getStage() {
        return stage;
    }
/**
 * 
 * @param args launches prog
 */

    public static void main(String[] args) {
        launch();
    }
}
