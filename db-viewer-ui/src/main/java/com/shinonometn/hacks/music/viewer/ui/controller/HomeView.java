package com.shinonometn.hacks.music.viewer.ui.controller;

import com.shinonometn.hacks.music.viewer.Main;
import com.shinonometn.hacks.music.viewer.db.MusicRepo;
import com.shinonometn.hacks.music.viewer.db.netease.NeteaseMusicRepoFactory;
import com.shinonometn.hacks.music.viewer.ui.App;
import com.shinonometn.hacks.music.viewer.util.FxKit;
import com.shinonometn.hacks.music.viewer.util.I18n;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;

public class HomeView extends BorderPane {

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

        // Setup menu actions
        menuItemOpenDatabase.setOnAction(e -> openDB());
        menuItemCloseDatabase.setOnAction(e -> {
            if (getCenter() != null) closeCenterView();
        });

        // Add listener at the center of self
        centerProperty().addListener((observable, oldValue, newValue) -> {
            //If no session view, the Close menu disable.
            menuItemCloseDatabase.setDisable(newValue != null);
        });

        // Welcome view center button action
        OpenSessionView.getInstance().setButtonAction(e -> openDB());
    }

    private void openDB() {
        FileChooser fileChooser = new FileChooser();

        File selectedLocation = fileChooser.showOpenDialog(App.stage());

        if (selectedLocation != null) {
            createSession(selectedLocation.getAbsolutePath());
        }
    }

    private void createSession(String dbPath) {
        closeCenterView();

        try {
            MusicRepo musicRepo = new NeteaseMusicRepoFactory(dbPath).build();
            setCenter(new EditSessionView(musicRepo));
        } catch (Exception e) {
            Main.LOGGER.error("", e);
            new Alert(Alert.AlertType.ERROR, I18n.i18n("msg.create_session_view_failed"), ButtonType.OK).showAndWait();
        }
    }

    private void closeCenterView() {
        Node center = getCenter();

        if (center != null) {
            if (center instanceof EditSessionView) {
                ((EditSessionView) center).close();
            }

            setCenter(null);
        }
    }
}
