package com.shinonometn.hacks.music.viewer.ui.controller;

import com.shinonometn.hacks.music.viewer.Main;
import com.shinonometn.hacks.music.viewer.db.MusicRepo;
import com.shinonometn.hacks.music.viewer.db.netease.NeteaseMusicRepoFactory;
import com.shinonometn.hacks.music.viewer.ui.App;
import com.shinonometn.hacks.music.viewer.ui.controller.database.DatabaseView;
import com.shinonometn.hacks.music.viewer.util.FxKit;
import com.shinonometn.hacks.music.viewer.util.I18n;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;

public class HomeView extends BorderPane {

    private final static HomeView instance = new HomeView();

    public static HomeView instance() {
        return instance;
    }

    /*
    *
    *
    *
    * */

    @FXML
    private TabPane tabPaneMain;

    private HomeView() {
        FxKit.load(this, "/ui/view/main.fxml");
    }

    @FXML
    private void initialize() {

    }


    public void createDatabaseView(DatabaseView targetDBView) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(App.stage());

        if (selectedFile != null) {
            try {
                MusicRepo musicRepo = new NeteaseMusicRepoFactory(selectedFile.getAbsolutePath()).build();
                targetDBView.changeMusicRepo(musicRepo);
            } catch (Exception e) {
                Main.LOGGER.error("", e);
                new Alert(Alert.AlertType.ERROR, I18n.i18n("msg.create_session_view_failed"), ButtonType.OK).showAndWait();
            }
        }
    }

    public void createDatabaseView() {
        // TODO
    }
}
