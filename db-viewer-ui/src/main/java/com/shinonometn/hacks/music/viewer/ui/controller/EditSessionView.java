package com.shinonometn.hacks.music.viewer.ui.controller;

import com.shinonometn.hacks.music.viewer.db.MusicRepo;
import com.shinonometn.hacks.music.viewer.info.PlayList;
import com.shinonometn.hacks.music.viewer.info.PlayerUser;
import com.shinonometn.hacks.music.viewer.info.TrackInfo;
import com.shinonometn.hacks.music.viewer.ui.App;
import com.shinonometn.hacks.music.viewer.ui.component.TextPropertyListCell;
import com.shinonometn.hacks.music.viewer.ui.controller.dialog.SelectUserDialog;
import com.shinonometn.hacks.music.viewer.util.FxKit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

import java.util.List;

public class EditSessionView extends SplitPane {

    private MusicRepo musicRepo;
    private PlayerUser playerUser;

    private List<PlayerUser> playerUsers;

    @FXML
    private ListView<PlayList> listPlayList;
    private ObservableList<PlayList> playListContainer = FXCollections.observableArrayList();

    @FXML
    private Label labelCurrentUser;

    @FXML
    private Button btnChangeUser;

    @FXML
    private BorderPane playlistView;

    public EditSessionView(MusicRepo musicRepo, PlayerUser playerUser) {
        this.musicRepo = musicRepo;
        this.playerUser = playerUser;

        this.playerUsers = musicRepo.getUsers();

        FxKit.load(this, "/ui/view/editSession.fxml");
    }

    @FXML
    private void initialize() {
        labelCurrentUser.setText(playerUser.getAccount());

        if (playerUsers.size() <= 0) {
            btnChangeUser.setVisible(false);
        } else {
            btnChangeUser.setOnAction(e -> changeUser());
        }

        listPlayList.setCellFactory(param -> TextPropertyListCell.of(PlayList::getTitle));
        listPlayList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                showPlayList(newValue);
            }
        });
        listPlayList.setItems(playListContainer);

        playListContainer.addAll(musicRepo.getLists(playerUser));

    }

    private void changeUser() {
        if(playlistView.getCenter() != null){
            playlistView.setCenter(null);
        }

        PlayerUser selectedUser = new SelectUserDialog().show(App.stage(), playerUsers);

        if(selectedUser != null){
            playListContainer.clear();
            playListContainer.addAll(musicRepo.getLists(selectedUser));

            labelCurrentUser.setText(selectedUser.getAccount());
            playerUser = selectedUser;
        }
    }

    private void showPlayList(PlayList playList) {
        if(playlistView.getCenter() != null){
            playlistView.setCenter(null);
        }
        List<TrackInfo> trackInfoList = musicRepo.getTracks(playList);
        playlistView.setCenter(new PlayListView(playList,trackInfoList));
    }

    public void close() {

    }
}
