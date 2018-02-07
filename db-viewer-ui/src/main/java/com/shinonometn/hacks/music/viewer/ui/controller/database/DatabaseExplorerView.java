package com.shinonometn.hacks.music.viewer.ui.controller.database;

import com.shinonometn.hacks.music.viewer.db.MusicRepo;
import com.shinonometn.hacks.music.viewer.info.PlayList;
import com.shinonometn.hacks.music.viewer.info.PlayerUser;
import com.shinonometn.hacks.music.viewer.ui.App;
import com.shinonometn.hacks.music.viewer.ui.component.TextPropertyListCell;
import com.shinonometn.hacks.music.viewer.ui.controller.dialog.SelectUserDialog;
import com.shinonometn.hacks.music.viewer.ui.controller.dialog.SessionInfoDialog;
import com.shinonometn.hacks.music.viewer.util.FxKit;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

import java.util.List;

import static com.shinonometn.hacks.music.viewer.util.I18n.i18n;

public class DatabaseExplorerView extends SplitPane {

    // Current music repo
    private Property<MusicRepo> musicRepoProperty = new SimpleObjectProperty<>();

    // All PlayerUsers in the database
    private ObservableList<PlayerUser> playerUserList = FXCollections.observableArrayList();

    // Current PlayerUser
    private Property<PlayerUser> playerUser = new SimpleObjectProperty<>();
    // PlayLists for current user
    private ObservableList<PlayList> playLists = FXCollections.observableArrayList();

    @FXML
    private ListView<PlayList> listPlayList;

    @FXML
    private Label labelCurrentUser;

    @FXML
    private Button btnChangeUser;

    @FXML
    private Button btnSessionInfo;

    @FXML
    private BorderPane playlistView;

    public DatabaseExplorerView(MusicRepo musicRepo) {
        FxKit.load(this, "/ui/view/databaseExplorer.fxml");

        // Set current MusicRepo, the UI will load all things automatically
        this.musicRepoProperty.setValue(musicRepo);
    }

    @FXML
    private void initialize() {

        // When the music repo changed...
        musicRepoProperty.addListener((observable, oldValue, newValue) -> this.playerUserList.addAll(newValue.getUsers()));

        // When the list of PlayerUser changed...
        playerUserList.addListener((ListChangeListener<PlayerUser>) c -> {
            List<? extends PlayerUser> list = c.getList();

            if (list.size() <= 0) {
                btnChangeUser.setVisible(false);
            } else {
                btnChangeUser.setOnAction(e -> changeUser());
                changeUser();
            }

        });

        // When session current user changed...
        playerUser.addListener((observable, oldValue, newValue) -> {

            // Set up labels
            labelCurrentUser.setText(i18n("edit.currentUser.template", newValue.getAccount()));

            // Fill the listView with new user's list
            playLists.clear();
            playLists.addAll(musicRepoProperty.getValue().getLists(newValue));

        });

        // When the content of PlayList changed...
        playLists.addListener((ListChangeListener<PlayList>) c -> {
            // Clear PlayList view
            if (playlistView.getCenter() != null) {
                playlistView.setCenter(null);
            }
        });

        // Set up button actions
        btnSessionInfo.setOnAction(e -> new SessionInfoDialog(i18n("dialog.sessionInfo.content.template", musicRepoProperty.getValue().getProviderName()))
                .showAndWait());

        // Set up PlayList list.
        listPlayList.setCellFactory(param -> TextPropertyListCell.of(PlayList::getTitle));
        listPlayList.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) showPlayList(newValue);
                });
        listPlayList.setItems(playLists);
    }

    // Change current user
    private void changeUser() {

        PlayerUser selectedUser = new SelectUserDialog().show(App.stage(), playerUserList);

        if (selectedUser != null) {
            playerUser.setValue(selectedUser);
        }
    }

    // Put playlist on right
    private void showPlayList(PlayList playList) {
        Node node = playlistView.getCenter();
        PlayListView playListView;
        if (node == null || !(node instanceof PlayListView)) {
            playListView = new PlayListView(p -> musicRepoProperty.getValue().getTracks(p));
            playlistView.setCenter(playListView);
        } else {
            playListView = (PlayListView) playlistView.getCenter();
        }

        playListView.setPlayList(playList);
    }

    public void changeMusicRepo(MusicRepo musicRepo){
        this.musicRepoProperty.setValue(musicRepo);
    }

    public void close() {

    }
}
