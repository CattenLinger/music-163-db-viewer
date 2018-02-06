package com.shinonometn.hacks.music.viewer.ui.controller;

import com.shinonometn.hacks.music.viewer.db.MusicRepo;
import com.shinonometn.hacks.music.viewer.info.PlayList;
import com.shinonometn.hacks.music.viewer.info.PlayerUser;
import com.shinonometn.hacks.music.viewer.ui.component.TextPropertyListCell;
import com.shinonometn.hacks.music.viewer.util.FxKit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;

public class EditSessionView extends SplitPane {

    private MusicRepo musicRepo;
    private PlayerUser playerUser;

    @FXML
    private ListView<PlayList> listPlayList;
    private ObservableList<PlayList> playListContainer = FXCollections.observableArrayList();

    public EditSessionView(MusicRepo musicRepo, PlayerUser playerUser) {
        this.musicRepo = musicRepo;
        this.playerUser = playerUser;

        FxKit.load(this, "/ui/view/editSession.fxml");
    }

    @FXML
    private void initialize() {

        listPlayList.setCellFactory(param -> TextPropertyListCell.of(PlayList::getTitle));
        listPlayList.setItems(playListContainer);

        playListContainer.addAll(musicRepo.getLists(playerUser));

    }

    public void close() {

    }
}
