package com.shinonometn.hacks.music.viewer.ui.controller.storage;

import com.shinonometn.hacks.music.viewer.util.FxKit;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class LibraryMainView extends BorderPane{

    @FXML
    private ListView<?> playlistView;

    public LibraryMainView() {
        FxKit.load(this,"/ui/view/localStorage/localStorage.xml");
    }

    @FXML
    private void initialize(){
        setCenter(new LibraryWelcomeView());
    }

}
