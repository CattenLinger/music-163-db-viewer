package com.shinonometn.hacks.music.viewer.util;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class FxKit {

    public static final Logger logger = LoggerFactory.getLogger(FxKit.class);

    public static void runInFX(Runnable doRun){
        if(Platform.isFxApplicationThread())
            doRun.run();
        else
            Platform.runLater(doRun);
    }

    public static FXMLLoader getLoader(String fxmlPath){
        return new FXMLLoader(FxKit.class.getResource(fxmlPath), I18n.UI_MAIN_BUNDLE);
    }

    public static FXMLLoader getLoader(Node node, String fxmlPath){
        return new FXMLLoader(node.getClass().getResource(fxmlPath), I18n.UI_MAIN_BUNDLE);
    }

    public static <T> T load(String fxmlPath) throws IOException {
        FXMLLoader loader = getLoader(fxmlPath);

        return loader.load();
    }

    public static <T> T load(Node node, String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(node.getClass().getResource(fxmlPath), I18n.UI_MAIN_BUNDLE);
        loader.setRoot(node);
        loader.setController(node);
        return loader.load();
    }

    public static void error(String message){
        new Alert(Alert.AlertType.ERROR, message, ButtonType.OK).showAndWait();
    }
}
