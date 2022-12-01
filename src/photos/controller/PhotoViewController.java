package photos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import photos.PhotosMain;
import photos.model.Album;
import photos.model.Date;
import photos.model.Photo;
import photos.model.Tag;

import java.io.*;

/**
 * Represents the photo view controller.
 * @author Brandon Perez bkp48 
 * @author Julian Calle joc24
 */
public class PhotoViewController {
    /**
     * Represents the image view.
     */
    @FXML
    private ImageView imageView;
    /**
     * Represents caption text field.
     */
    @FXML
    private TextField captionText;

    /**
     * Represents the date text field.
     */
    @FXML
    private TextField dateText;

    /**
     * Represents the photo list view.
     */
    @FXML
    private ListView<Photo> photoList;

    /**
     * Represents the user input photo text field.
     */
    @FXML
    private TextField userInputPhoto;

    /**
     * Represents the album list view.
     */
    @FXML
    private ListView<Album> albumList;

    /**
     * Represents the tag list view.
     */
    @FXML
    private ListView<Tag> tagList;

    /**
     * Represents the user input tag text field.
     */
    @FXML
    private TextField userInputTag;

    /**
     * Represents the user input tag search.
     */
    @FXML
    private TextField userInputTagSearch;

    /**
     * Represents the initial date selected.
     */
    @FXML
    private DatePicker fromSearch;

    /**
     * Represents the final date selected.
     */
    @FXML
    private DatePicker toSearch;

    /**
     * Represents the user input album text field.
     */
    @FXML
    private TextField userInputAlbum;

    /**
     * Switches scene to slideshow.
     * @throws IOException
     */
    @FXML
    private void onSlideshow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PhotosMain.class.getResource("/photos/resources/Slideshow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SlideshowController controller = fxmlLoader.getController();
        if (photoList.getItems() == null) {
            return;
        }
        controller.getPhotoList().addAll(photoList.getItems());
        controller.setIndex(0);
        Image image = loadImage(controller.getPhotoList().get(controller.getIndex()).getPath());
        if (image == null) {
            return;
        }
        controller.getImageView().setImage(image);
        PhotosMain.getStage().setScene(scene);
    }

    /**
     * Adds a photo to list.
     */
    @FXML
    private void onAddPhoto() {
        if (userInputPhoto.getText().isBlank()) {
            return;
        }
        String fileLocation = userInputPhoto.getText();
        File file = new File(fileLocation);
        if (!file.exists() || file.isDirectory()) {
            return;
        }
        for (Photo cmp : AlbumListController.getCurrentAlbum().getPhotoList()) {
            if (cmp.getPath().equals(fileLocation)) {
                return;
            }
        }
        Photo photo = new Photo(fileLocation);
        AlbumListController.getCurrentAlbum().getPhotoList().add(photo);
        photoList.setItems(FXCollections.observableArrayList(AlbumListController.getCurrentAlbum().getPhotoList()));
        photoList.setCellFactory(photoListView -> new ImageStringView());
        userInputPhoto.setText("");

        //update Album list
        ObservableList<Album> newList = FXCollections.observableList(LogInController.getCurrentUser().getAlbumList());
        setAlbumList(newList);

    }

    /**
     * Removes photo from list.
     */
    @FXML
    private void onRemovePhoto() {
        Photo photo = photoList.getSelectionModel().getSelectedItem();
        Album album = AlbumListController.getCurrentAlbum();
        album.getPhotoList().remove(photo);
        photoList.setItems(FXCollections.observableArrayList(album.getPhotoList()));
        imageView.setImage(null);
    }

    /**
     * Displays photo mouse clicked on.
     * @throws FileNotFoundException
     */
    @FXML
    private void onPhotoListMouseClicked() throws FileNotFoundException {
        Photo photo = photoList.getSelectionModel().getSelectedItem();
        if (photo == null) {
            return;
        }
        if (loadImage(photo.getPath()) == null) {
            return;
        }
        imageView.setImage(loadImage(photo.getPath()));
        captionText.setText(photo.getCaption());
        dateText.setText(photo.getDate().toString());
        // tag area
        ObservableList<Tag> newTagList = FXCollections.observableList(photo.getTags());
        tagList.setItems(newTagList);
    }

    /**
     * Captions or re-captions a photo.
     */
    @FXML
    private void onCaptionOrRecaption() {
        Photo photo = photoList.getSelectionModel().getSelectedItem();
        photo.setCaption(userInputPhoto.getText());
        photoList.setItems(FXCollections.observableArrayList(AlbumListController.getCurrentAlbum().getPhotoList()));
        userInputPhoto.setText("");
        captionText.setText(photo.getCaption());
    }

    /**
     * Adds a tag to photo.
     */
    @FXML
    private void onAddTag() {
        if (userInputTag.getText().isBlank()) {
            return;
        }
        Photo photo = photoList.getSelectionModel().getSelectedItem();
        if (photo == null) {
            return;
        }
        String userInput = userInputTag.getText();
        String[] tokens = userInput.split("[=,:-]+");

        try {
            Tag tag = new Tag(tokens[0].toLowerCase().trim(), tokens[1].trim());
            if (photo.getTags().contains(tag)) {
                return;
            }
            photo.getTags().add(tag);
        } catch (ArrayIndexOutOfBoundsException e) {
            // Add Alert
            System.out.println("Illegal expression for add.");
        }

        userInputTag.setText("");
        // tag area
        ObservableList<Tag> newTagList = FXCollections.observableList(photo.getTags());
        tagList.setItems(newTagList);

    }

    /**
     * Removes tag from photo.
     */
    @FXML
    private void onRemoveTag() {
        if (photoList.getSelectionModel().getSelectedItem() != null && tagList.getSelectionModel().getSelectedItem() != null) {
            photoList.getSelectionModel().getSelectedItem().getTags().remove(tagList.getSelectionModel().getSelectedItem());
            ObservableList<Tag> newTagList = FXCollections.observableList(photoList.getSelectionModel().getSelectedItem().getTags());
            tagList.setItems(newTagList);
        }
    }

    /**
     * Searches for photos with tag.
     */
    @FXML
    private void onTagSearch() {
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
                } else if (cmp.equalsIgnoreCase("and")) {
                    // CALL AND FUNC
                    conjunctionAnd(userInputTagSearch.getText());
                    return;
                }
            }
            // call normal func
            normalTagSearch(userInputTagSearch.getText());
        }
    }

    /**
     * Searches for photos within date range.
     */
    @FXML
    private void onDateSearch() {
        if (fromSearch.getValue() == null || toSearch.getValue() == null) return;

        Date from = new Date(fromSearch.getValue().toString());
        Date to = new Date(toSearch.getValue().toString());

        ObservableList<Photo> newList = FXCollections.observableArrayList();

        for (Photo photo : AlbumListController.getCurrentAlbum().getPhotoList()) {
            if (photo.getDate().getLocalDate().compareTo(from.getLocalDate()) >= 0 &&
                    photo.getDate().getLocalDate().compareTo(to.getLocalDate()) <= 0) {
                newList.add(photo);
            }
        }

        setPhotoList(newList);

    }

    /**
     * Copies photo from one album to another.
     */
    @FXML
    private void onCopyPhoto() {
        if (albumList.getSelectionModel().getSelectedItem() != null && photoList.getSelectionModel().getSelectedItem() != null) {
            if (albumList.getSelectionModel().getSelectedItem().getPhotoList().contains(photoList.getSelectionModel().getSelectedItem())) {
                return;
            }
            albumList.getSelectionModel().getSelectedItem().getPhotoList().add(photoList.getSelectionModel().getSelectedItem());
            ObservableList<Album> newList = FXCollections.observableArrayList(LogInController.getCurrentUser().getAlbumList());
            setAlbumList(newList);
        }

    }

    /**
     * Moves photo from one album to another.
     */
    @FXML
    private void onMovePhoto() {
        if (albumList.getSelectionModel().getSelectedItem() != null && photoList.getSelectionModel().getSelectedItem() != null) {
            if (albumList.getSelectionModel().getSelectedItem().getPhotoList().contains(photoList.getSelectionModel().getSelectedItem())) {
                return;
            }
            albumList.getSelectionModel().getSelectedItem().getPhotoList().add(photoList.getSelectionModel().getSelectedItem());
            AlbumListController.getCurrentAlbum().getPhotoList().remove(photoList.getSelectionModel().getSelectedItem());
            ObservableList<Album> newAlbumList = FXCollections.observableList(LogInController.getCurrentUser().getAlbumList());
            ObservableList<Photo> newPhotoList = FXCollections.observableList(AlbumListController.getCurrentAlbum().getPhotoList());
            setAlbumList(newAlbumList);
            setPhotoList(newPhotoList);
        }
    }

    /**
     * Displays the original photo list.
     */
    @FXML
    private void onDisplayOriginalList() {
        ObservableList<Photo> newList = FXCollections.observableList(AlbumListController.getCurrentAlbum().getPhotoList());
        setPhotoList(newList);
    }

    /**
     * Creates a new album within photo view.
     */
    @FXML
    private void onCreateNewAlbum() {
        if (userInputAlbum.getText().isBlank()) {
            return;
        }
        String albumName = userInputAlbum.getText();
        Album album = new Album(albumName);
        if (LogInController.getCurrentUser().getAlbumList().contains(album)) {
            return;
        }
        album.getPhotoList().addAll(photoList.getItems());
        LogInController.getCurrentUser().getAlbumList().add(album);
        ObservableList<Album> newList = FXCollections.observableList(LogInController.getCurrentUser().getAlbumList());
        setAlbumList(newList);
    }

    /**
     * Returns to album list view scene.
     * @throws IOException
     */
    @FXML
    private void onBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PhotosMain.class.getResource("/photos/resources/AlbumList.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AlbumListController controller = fxmlLoader.getController();
        ObservableList<Album> newList = FXCollections.observableArrayList(LogInController.getCurrentUser().getAlbumList());
        controller.setAlbumList(newList);
        PhotosMain.getStage().setScene(scene);
    }

    /**
     * Logs out user from program.
     */
    @FXML
    private void onLogOut() {
        PhotosMain.switchScene(SceneType.LOGIN);
    }

    /**
     * Forms the photo list view layout.
     */
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
                try {
                    if (loadImage(item.getPath()) == null) {
                        return;
                    }
                    thumbnail.setImage(loadImage(item.getPath()));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                setGraphic(thumbnail);
                setText(item.getCaption());
            }
        }
    }

    /**
     * Disjunction tag search.
     * @param input user tag search input.
     */
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
            if (photo.getTags().contains(cmp1) || photo.getTags().contains(cmp2)) {
                newList.add(photo);
            }
        }
        if (newList.size() == 0) {
            return;
        }
        setPhotoList(newList);
    }

    /**
     * Conjunction tag search
     * @param input user tag search input.
     */
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
            if (photo.getTags().contains(cmp1) && photo.getTags().contains(cmp2)) {
                newList.add(photo);
            }
        }
        if (newList.size() == 0) {
            return;
        }
        setPhotoList(newList);
    }
    /**
     * searches the image using the tag
     * @param input represents user tag input.
     */

    private void normalTagSearch(String input) {
        ObservableList<Photo> newList = FXCollections.observableArrayList();
        String[] tokens = input.trim().split("=");
        String key, value;
        key = tokens[0].trim();
        value = tokens[1].trim();
        Tag cmp = new Tag(key, value);
        for (Photo photo : AlbumListController.getCurrentAlbum().getPhotoList()) {
            if (photo.getTags().contains(cmp)) {
                newList.add(photo);
            }
        }
        if (newList.size() == 0) {
            return;
        }
        setPhotoList(newList);

    }
    /**
     * Loads image with given path.
    * @param path Represents path of image.
    * @return Image Returns image of path.
    * @throws FileNotFoundException
    */
    
    public static Image loadImage (String path) throws FileNotFoundException {
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            return null;
        }
        InputStream stream = new FileInputStream(path);
        return new Image(stream);
    }

/**
 * list to update listview.
 * @param newList Represents the new updated list.
 */
    public void setPhotoList(ObservableList<Photo> newList) {
        photoList.setItems(newList);
        photoList.setCellFactory(photoListView -> new ImageStringView());
    }
/**
 * List to update listview.
 * @param newList Represents the new updated list.
 */

    public void setAlbumList(ObservableList<Album> newList) {
        albumList.setItems(newList);
    }
}
