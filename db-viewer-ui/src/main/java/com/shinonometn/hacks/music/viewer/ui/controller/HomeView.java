package com.shinonometn.hacks.music.viewer.ui.controller;

import com.shinonometn.hacks.music.viewer.Main;
import com.shinonometn.hacks.music.viewer.db.MusicRepo;
import com.shinonometn.hacks.music.viewer.db.netease.NeteaseMusicRepoFactory;
import com.shinonometn.hacks.music.viewer.info.PlayerUser;
import com.shinonometn.hacks.music.viewer.ui.App;
import com.shinonometn.hacks.music.viewer.ui.controller.dialog.SelectUserDialog;
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
import java.util.List;

public class HomeView extends BorderPane {

    private EditSessionView sessionView;

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

        // Setup button actions
        menuItemOpenDatabase.setOnAction(e -> openDB());

        // Welcome view center button acion
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
        closeSessionView();

        try {
            MusicRepo musicRepo = new NeteaseMusicRepoFactory(dbPath).build();

            List<PlayerUser> playerUsers = musicRepo.getUsers();
            if (playerUsers.size() == 0) {
                new Alert(Alert.AlertType.ERROR, I18n.i18n("msg.database_no_user"), ButtonType.OK).showAndWait();
            } else {
                PlayerUser selectedUser;
                if (playerUsers.size() > 1) {
                    selectedUser = playerUsers.get(0);
                } else {
                    selectedUser = new SelectUserDialog().show(App.stage(), playerUsers);
                }

                if (selectedUser != null) {
                    setSessionView(new EditSessionView(musicRepo, selectedUser));
                }
            }
        } catch (Exception e) {
            Main.LOGGER.error("",e);
            new Alert(Alert.AlertType.ERROR, I18n.i18n("msg.create_session_view_failed"), ButtonType.OK).showAndWait();
        }
    }

    private void closeSessionView() {
        Node center = getCenter();

        if(center instanceof EditSessionView){
            EditSessionView editSessionView = getSessionView();
            if (editSessionView != null) {
                editSessionView.close();
                setSessionView(null);
            }
        }else{
            setCenter(null);
        }
    }

    private void setSessionView(EditSessionView editSessionView) {
        this.setCenter(editSessionView);
    }

    private EditSessionView getSessionView() {
        return (EditSessionView) this.getCenter();
    }
}
