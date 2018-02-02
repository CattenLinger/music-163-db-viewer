package com.shinonometn.hacks.music.viewer.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FxKit {

    public static final Logger logger = LoggerFactory.getLogger(FxKit.class);

    public static FXMLLoader getLoader(String fxmlPath){
        return new FXMLLoader(FxKit.class.getResource(fxmlPath), I18n.UI_MAIN_BUNDLE);
    }

    public static FXMLLoader load(String fxmlPath){
        FXMLLoader loader = getLoader(fxmlPath);

        try {
            loader.load();
        }catch (Exception e){
            logger.error(String.format("Error loading fxml %s",fxmlPath),e);
            error(e.toString());
        }

        return loader;
    }

    public static void error(String message){
        new Alert(Alert.AlertType.ERROR, message, ButtonType.OK).showAndWait();
    }
}
