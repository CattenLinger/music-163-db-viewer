package com.shinonometn.hacks.music.viewer.ui.controller;

import com.shinonometn.hacks.music.viewer.util.FxKit;
import javafx.scene.control.SplitPane;

public class EditSessionView extends SplitPane {

    public EditSessionView() {
        FxKit.load(this, "/ui/view/editSession.fxml");
    }
}
