package com.shinonometn.hacks.music.viewer.ui.controller;

import com.shinonometn.hacks.music.viewer.db.MusicRepo;
import com.shinonometn.hacks.music.viewer.db.MusicRepoFactory;
import com.shinonometn.hacks.music.viewer.db.netease.NeteaseMusicRepoFactory;
import com.shinonometn.hacks.music.viewer.ui.domain.EditSession;
import com.shinonometn.hacks.music.viewer.util.FxKit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class HomeView extends BorderPane {

    private EditSession currentSession;

    @FXML
    private MenuItem menuItemOpenDatabase;

    @FXML
    private MenuItem menuItemCloseDatabase;

    public HomeView() throws IOException {
        FxKit.load(this,"/ui/view/main.fxml");

        this.setCenter(OpenSessionView.getInstance());
    }

    @FXML
    private void initialize(){
        menuItemOpenDatabase.addActionListener(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
        });
    }

    private void createSession(String dbPath) throws Exception {
        if(currentSession != null){
            throw new IllegalStateException("Session opened");
        }

        MusicRepo musicRepo = new NeteaseMusicRepoFactory(dbPath).build();
        this.currentSession = new EditSession(musicRepo);

    }

    private void closeSession(){
        this.currentSession = null;
    }
}
