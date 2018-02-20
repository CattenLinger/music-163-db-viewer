package com.shinonometn.hacks.music.viewer.ui.controller.database;

import com.shinonometn.hacks.music.viewer.util.FxKit;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ExplorerWelcomeView extends VBox {

    @FXML
    private Button theCenterButton;

    public ExplorerWelcomeView() {
        FxKit.load(this, "/ui/view/database/explorerWelcome.fxml");
    }

    public void setButtonAction(EventHandler<ActionEvent> eventHandler) {
        theCenterButton.setOnAction(eventHandler);
    }

}
