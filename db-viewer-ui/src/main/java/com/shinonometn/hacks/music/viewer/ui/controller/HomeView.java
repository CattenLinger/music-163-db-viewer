package com.shinonometn.hacks.music.viewer.ui.controller;

import com.shinonometn.hacks.music.viewer.db.MusicRepo;
import com.shinonometn.hacks.music.viewer.db.netease.NeteaseMusicRepoFactory;
import com.shinonometn.hacks.music.viewer.info.PlayerUser;
import com.shinonometn.hacks.music.viewer.ui.App;
import com.shinonometn.hacks.music.viewer.ui.controller.dialog.SelectUserDialog;
import com.shinonometn.hacks.music.viewer.ui.domain.EditSession;
import com.shinonometn.hacks.music.viewer.util.FxKit;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HomeView extends BorderPane {

    private EditSession currentSession;

    @FXML
    private MenuItem menuItemOpenDatabase;

    @FXML
    private MenuItem menuItemCloseDatabase;

    public HomeView() {
        FxKit.load(this, "/ui/view/main.fxml");

        this.setCenter(OpenSessionView.getInstance());
    }

    @FXML
    private void initialize() {
        menuItemOpenDatabase.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedLocation = directoryChooser.showDialog(App.stage());
            if (selectedLocation != null) {
                System.out.println(selectedLocation.getAbsolutePath());
            }

            PlayerUser playerUser = new SelectUserDialog().show(App.stage(),
                    IntStream
                            .range(0, 10)
                            .mapToObj(i -> new PlayerUser() {
                                @Override
                                public String getAccount() {
                                    return String.valueOf(i);
                                }
                            }).collect(Collectors.toList()));

            System.out.println(playerUser);
        });

        OpenSessionView.getInstance().setButtonAction(menuItemOpenDatabase.getOnAction());
    }

    private void createSession(String dbPath) throws Exception {
        if (currentSession != null) {
            throw new IllegalStateException("Session opened");
        }

        MusicRepo musicRepo = new NeteaseMusicRepoFactory(dbPath).build();
        this.currentSession = new EditSession(musicRepo);

    }

    private void closeSession() {
        this.currentSession = null;
    }
}
