package com.shinonometn.hacks.music.viewer.util;

import com.shinonometn.hacks.music.viewer.utils.FunctionUtils;
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

    /**
     *
     * Get loader with a path to a fxml file
     *
     * @param fxmlPath classpath to fxml file
     * @return FXMLLoader
     */
    public static FXMLLoader getLoader(String fxmlPath){
        return new FXMLLoader(FxKit.class.getResource(fxmlPath), I18n.UI_MAIN_BUNDLE);
    }

    public static FXMLLoader getLoader(Node node, String fxmlPath){
        return new FXMLLoader(node.getClass().getResource(fxmlPath), I18n.UI_MAIN_BUNDLE);
    }

    /**
     *
     * Load a node from a fxml file
     *
     * @param fxmlPath classpath
     * @param <T> what is the root node of the fxml file
     * @return the root node object of the xml file
     * @throws IOException FXML loader exception
     */
    public static <T> T load(String fxmlPath) throws IOException {
        FXMLLoader loader = getLoader(fxmlPath);

        return loader.load();
    }

    /**
     *
     * Initialize a Fx node with a fxml file.
     *
     * @param node the node object
     * @param fxmlPath classpath to node xml file
     * @param <T> what the node object exactly is
     * @return the root node object of the xml file
     */
    public static <T> T load(Node node, String fxmlPath){
        FXMLLoader loader = new FXMLLoader(node.getClass().getResource(fxmlPath), I18n.UI_MAIN_BUNDLE);
        loader.setRoot(node);
        loader.setController(node);
        return FunctionUtils.invoke(loader::load);
    }

    public static void error(String message){
        new Alert(Alert.AlertType.ERROR, message, ButtonType.OK).showAndWait();
    }
}
