package photos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import photos.controller.SceneType;
import photos.model.User;

import java.io.*;
import java.util.ArrayList;
/**
 * Represents the main class.
 * @author Brandon Perez bkp48 
 * @author Julian Calle joc24
 */
public class PhotosMain extends Application {
/**
 * Represents the userlist
 */
    private static ArrayList<User> userList;
    /**
     * Represents the stage.
     */
    private static Stage stage;
     /**
     * Loads the root scene when launched.
     */
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
/**
 * Finds the file which holds the users.
 */
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
 * Locates the file users are stored in and adds if needed.
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
 * Changes the scene for user.
 * @param sceneType
 */

    public static void switchScene (SceneType sceneType) {
        stage.setScene(sceneType.getScene());
    }
/**
 * checks if there is a current user.
 * @return userList
 */

    public static ArrayList<User> getUserList() {
        return userList;
    }
/**
* selects the stage for user.
* @return stage
*/
    public static Stage getStage() {
        return stage;
    }
/**
 * launches the program
 * @param args 
 */

    public static void main(String[] args) {
        launch();
    }
}
