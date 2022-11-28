package photos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import photos.PhotosMain;
import photos.model.Album;
import photos.model.Photo;
import photos.model.Tag;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

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
    private ListView<Album> albumList;

    @FXML
    private ListView<Tag> tagList;

    @FXML
    private TextField userInputTag;

    @FXML
    private TextField userInputTagSearch;

    @FXML
    private TextField userInputDateSearch;

    @FXML
    private TextField userInputAlbum;
    @FXML
    private void onSlideshow() {
        PhotosMain.switchScene(SceneType.SLIDESHOW);
    }

    @FXML
    private void onAddPhoto() {
        if (userInputPhoto.getText().isBlank()) {
            return;
        }
        try {
            String fileLocation = userInputPhoto.getText();
            for (Photo cmp : AlbumListController.getCurrentAlbum().getPhotoList()) {
                if (cmp.getPath().equals(fileLocation)) {
                    return;
                }
            }
            InputStream stream = new FileInputStream(fileLocation);
            Image image = new Image(stream);
            Photo photo = new Photo(image, fileLocation);
            AlbumListController.getCurrentAlbum().getPhotoList().add(photo);
            photoList.setItems(FXCollections.observableArrayList(AlbumListController.getCurrentAlbum().getPhotoList()));
            photoList.setCellFactory(photoListView -> new ImageStringView());
            userInputPhoto.setText("");

            //update Album list
            ObservableList<Album> newList = FXCollections.observableList(LogInController.getCurrentUser().getAlbumList());
            setAlbumList(newList);

        } catch (IOException e) {
            // ADD ALERT
            System.out.println("Invalid file path");
        }
    }
    @FXML
    private void onRemovePhoto() {
        Photo photo = photoList.getSelectionModel().getSelectedItem();
        Album album = AlbumListController.getCurrentAlbum();
        album.getPhotoList().remove(photo);
        photoList.setItems(FXCollections.observableArrayList(album.getPhotoList()));
        imageView.setImage(null);
    }

    @FXML
    private void onPhotoListMouseClicked() {
        Photo photo = photoList.getSelectionModel().getSelectedItem();
        if (photo == null) {
            return;
        }
        imageView.setImage(photo.getImage());
        captionText.setText(photo.getCaption());
        dateText.setText(photo.toStringDate());
        // tag area
        ObservableList<Tag> newTagList = FXCollections.observableList(photo.getNewTags());
        tagList.setItems(newTagList);
    }

    @FXML
    private void onCaptionOrRecaption() {
        Photo photo = photoList.getSelectionModel().getSelectedItem();
        photo.setCaption(userInputPhoto.getText());
        photoList.setItems(FXCollections.observableArrayList(AlbumListController.getCurrentAlbum().getPhotoList()));
        userInputPhoto.setText("");
        captionText.setText(photo.getCaption());
    }

    @FXML
    private void onAddTag() {
        if (userInputTag.getText().isBlank()) {
            return;
        }
        Photo photo = photoList.getSelectionModel().getSelectedItem();
        String userInput = userInputTag.getText();
        String[] tokens = userInput.split("[=:-]+");

        try {
            Tag tag = new Tag(tokens[0].toLowerCase().trim(), tokens[1].trim());
            if (photo.getNewTags().contains(tag)) {
                return;
            }
            photo.getNewTags().add(tag);
        } catch (ArrayIndexOutOfBoundsException e) {
            // Add Alert
            System.out.println("Illegal expression for add.");
        }

        userInputTag.setText("");
        // tag area
        ObservableList<Tag> newTagList = FXCollections.observableList(photo.getNewTags());
        tagList.setItems(newTagList);

    }

    @FXML
    private void onRemoveTag() {
        if (userInputTag.getText().isBlank()) {
            return;
        }
        Photo photo = photoList.getSelectionModel().getSelectedItem();
        String[] tokens = userInputTag.getText().split("[=:-]+");
        try {
            Tag tag = new Tag(tokens[0], tokens[1]);
            photo.getNewTags().remove(tag);
        } catch (ArrayIndexOutOfBoundsException e) {
            // Add alert
            System.out.println("Illegal expression for remove.");
        }
        // tag area
        userInputTag.setText("");
        ObservableList<Tag> newTagList = FXCollections.observableList(photo.getNewTags());
        tagList.setItems(newTagList);
    }

    @FXML
    private void onSearch() {
        // EXAMPLE: ("location","New Brunswick"), or ("person","susan")
        // EXAMPLE 2: person=john smith OR person=maya
        // EXAMPLE 3: 11/10/2021 - 11/10/2022
        if (!userInputTagSearch.getText().isBlank()) {
            String[] tokens = userInputTagSearch.getText().split(" ");
            for (String cmp : tokens) {
                if (cmp.equalsIgnoreCase("or")) {
                    // CALL OR FUNC
                    disjunctionOr(userInputTagSearch.getText());
                    return;
                }
                else if (cmp.equalsIgnoreCase("and")) {
                    // CALL AND FUNC
                    conjunctionAnd(userInputTagSearch.getText());
                    return;
                }
            }
            // call normal func
            normalTagSearch(userInputTagSearch.getText());
        }
    }
    @FXML
    private void onCopyPhoto() {
        if (userInputAlbum.getText().isBlank()) {
            return;
        }
        String inputAlbumName = userInputAlbum.getText();
        for (Album album : LogInController.getCurrentUser().getAlbumList()) {
            if (album.getAlbumName().equals(inputAlbumName)) {
                if (album.getPhotoList().contains(photoList.getSelectionModel().getSelectedItem())) {
                    return;
                }else {
                    album.getPhotoList().add(photoList.getSelectionModel().getSelectedItem());
                }
            }
        }
    }

    @FXML
    private void onMovePhoto() {
        if (userInputAlbum.getText().isBlank()) {
            return;
        }
        String inputAlbumName = userInputAlbum.getText();
        for (Album album : LogInController.getCurrentUser().getAlbumList()) {
            if (album.getAlbumName().equals(inputAlbumName)) {
                if (album.getPhotoList().contains(photoList.getSelectionModel().getSelectedItem())) {
                    return;
                }
                else {
                    album.getPhotoList().add(photoList.getSelectionModel().getSelectedItem());
                    AlbumListController.getCurrentAlbum().getPhotoList().remove(photoList.getSelectionModel().getSelectedItem());
                }
            }
        }
    }

    @FXML
    private void onDisplayOriginalList() {
        ObservableList<Photo> newList = FXCollections.observableList(AlbumListController.getCurrentAlbum().getPhotoList());
        setPhotoList(newList);
    }

    @FXML
    private void onCreateNewAlbum() {
        Album album = new Album("album"+PhotosMain.getCount());
        album.getPhotoList().addAll(photoList.getItems());
        LogInController.getCurrentUser().getAlbumList().add(album);
        ObservableList<Album> newList = FXCollections.observableList(LogInController.getCurrentUser().getAlbumList());
        setAlbumList(newList);
    }

    @FXML
    private void onLogOut() {
        PhotosMain.switchScene(SceneType.LOGIN);
    }

    static class ImageStringView extends ListCell<Photo> {
        @Override
        public void updateItem(Photo item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null) {
                setGraphic(null);
                setText(null);
            } else {
                ImageView thumbnail = new ImageView();
                thumbnail.setFitHeight(32);
                thumbnail.setFitWidth(32);
                thumbnail.setImage(item.getImage());
                setGraphic(thumbnail);
                setText(item.getCaption());
            }
        }
    }

    private void disjunctionOr(String input) {
        ObservableList<Photo> newList = FXCollections.observableArrayList();
        String[] tokens = input.split("OR|or");
        String[] keyPairToken1 = tokens[0].trim().split("[=:-]+");
        String[] keyPairToken2 = tokens[1].trim().split("[=:-]+");
        String key1, value1, key2, value2;
        key1 = keyPairToken1[0].trim();
        value1 = keyPairToken1[1].trim();
        key2 = keyPairToken2[0].trim();
        value2 = keyPairToken2[1].trim();
        Tag cmp1 = new Tag(key1, value1);
        Tag cmp2 = new Tag(key2, value2);
        for (Photo photo : AlbumListController.getCurrentAlbum().getPhotoList()) {
            if (photo.getNewTags().contains(cmp1) || photo.getNewTags().contains(cmp2)) {
                newList.add(photo);
            }
        }
        if (newList.size() == 0) {
            return;
        }
        setPhotoList(newList);
    }

    private void conjunctionAnd(String input) {
        ObservableList<Photo> newList = FXCollections.observableArrayList();
        String[] tokens = input.split("AND|and");
        String[] keyPairToken1 = tokens[0].trim().split("[=:-]+");
        String[] keyPairToken2 = tokens[1].trim().split("[=:-]+");
        String key1, value1, key2, value2;
        key1 = keyPairToken1[0].trim();
        value1 = keyPairToken1[1].trim();
        key2 = keyPairToken2[0].trim();
        value2 = keyPairToken2[1].trim();
        Tag cmp1 = new Tag(key1, value1);
        Tag cmp2 = new Tag(key2, value2);
        for (Photo photo : AlbumListController.getCurrentAlbum().getPhotoList()) {
            if (photo.getNewTags().contains(cmp1) && photo.getNewTags().contains(cmp2)) {
                newList.add(photo);
            }
        }
        if (newList.size() == 0) {
            return;
        }
        setPhotoList(newList);
    }

    private void normalTagSearch(String input) {
        ObservableList<Photo> newList = FXCollections.observableArrayList();
        String[] tokens = input.trim().split("=");
        String key, value;
        key = tokens[0].trim();
        value = tokens[1].trim();
        Tag cmp = new Tag(key, value);
        for (Photo photo : AlbumListController.getCurrentAlbum().getPhotoList()) {
            if (photo.getNewTags().contains(cmp)) {
                newList.add(photo);
            }
        }
        if (newList.size() == 0) {
            return;
        }
        setPhotoList(newList);

    }




    public void setPhotoList(ObservableList<Photo> newList) {
        photoList.setItems(newList);
        photoList.setCellFactory(photoListView -> new ImageStringView());
    }

    public void setAlbumList(ObservableList<Album> newList) {
        albumList.setItems(newList);
    }



}
