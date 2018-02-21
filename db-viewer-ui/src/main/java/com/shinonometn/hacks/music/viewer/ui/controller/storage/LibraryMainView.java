package com.shinonometn.hacks.music.viewer.ui.controller.storage;

import com.shinonometn.hacks.music.viewer.commons.ui.SectionTab;
import com.shinonometn.hacks.music.viewer.util.FxKit;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

@SectionTab("localStorage.title")
public class LibraryMainView extends SplitPane{

    @FXML
    private ListView<?> playlistView;

    @FXML
    private BorderPane mainPane;

    public LibraryMainView() {
        FxKit.load(this,"/ui/view/localStorage/localStorage.fxml");
    }

    @FXML
    private void initialize(){
        mainPane.setCenter(new LibraryWelcomeView());
    }

}
