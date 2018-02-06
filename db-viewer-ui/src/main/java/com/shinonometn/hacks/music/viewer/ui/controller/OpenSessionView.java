package com.shinonometn.hacks.music.viewer.ui.controller;

import com.shinonometn.hacks.music.viewer.util.FxKit;
import com.shinonometn.hacks.music.viewer.utils.FunctionUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class OpenSessionView extends VBox {

    /**
     * Singleton instance
     */
    private static OpenSessionView instance = FunctionUtils.invoke(OpenSessionView::new);

    public static OpenSessionView getInstance() {
        return instance;
    }

    @FXML
    private Button theCenterButton;

    public OpenSessionView() {
        FxKit.load(this, "/ui/view/openSession.fxml");
    }

    public void setButtonAction(EventHandler<ActionEvent> eventHandler) {
        theCenterButton.setOnAction(eventHandler);
    }

}
