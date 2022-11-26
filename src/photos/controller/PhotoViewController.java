package photos.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import photos.model.Album;
import photos.model.Photo;

public class PhotoViewController {

    @FXML
    private ImageView imageView;
    @FXML
    private TextField captionText;

    @FXML
    private TextField dateText;

    @FXML
    private ListView<Photo> photoList;

    @FXML
    private TextField userInputPhoto;

    @FXML
    private TextField userInputSearch;

    @FXML
    private ListView<Album> albumList;



}
