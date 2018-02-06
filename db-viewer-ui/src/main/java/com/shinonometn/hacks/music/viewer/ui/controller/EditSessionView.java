package com.shinonometn.hacks.music.viewer.ui.controller;

import com.shinonometn.hacks.music.viewer.db.MusicRepo;
import com.shinonometn.hacks.music.viewer.info.PlayList;
import com.shinonometn.hacks.music.viewer.info.PlayerUser;
import com.shinonometn.hacks.music.viewer.ui.component.TextPropertyListCell;
import com.shinonometn.hacks.music.viewer.util.FxKit;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;

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

    public EditSessionView(MusicRepo musicRepo, PlayerUser playerUser) {
        this.musicRepo = musicRepo;
        this.playerUser = playerUser;

        this.playerUsers = musicRepo.getUsers();

        FxKit.load(this, "/ui/view/editSession.fxml");
    }

    @FXML
    private void initialize() {
        labelCurrentUser.setText(playerUser.getAccount());

        if(playerUsers.size() <= 0){
            btnChangeUser.setVisible(false);
        } else {
            btnChangeUser.setOnAction(e -> changeUser());
        }

        listPlayList.setCellFactory(param -> TextPropertyListCell.of(PlayList::getTitle));
        listPlayList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPlayList(newValue));
        listPlayList.setItems(playListContainer);

        playListContainer.addAll(musicRepo.getLists(playerUser));

    }

    private void changeUser(){

    }

    private void showPlayList(PlayList playList){

    }

    public void close() {

    }
}
