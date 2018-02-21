package com.shinonometn.hacks.music.viewer.ui.controller.storage;

import com.shinonometn.hacks.music.viewer.util.FxKit;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * Created by Catten Linger on 2018/2/20.
 */
public class LibraryWelcomeView extends BorderPane{

    @FXML
    private Label labelTrackCount;

    @FXML
    private Label labelPlaylistCount;

    public LibraryWelcomeView() {
        FxKit.load(this,"/ui/view/localStorage/libraryWelcome.fxml");
    }
}
