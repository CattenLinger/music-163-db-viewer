package com.shinonometn.hacks.music.viewer.ui;

import com.shinonometn.hacks.music.viewer.util.AppCrashHandler;
import com.shinonometn.hacks.music.viewer.util.FxKit;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.shinonometn.hacks.music.viewer.util.I18n.i18n;

public class App {

    private static App instance;

    /**
     * Start the whole Fx App
     *
     * @param stage primary stage
     * @return primary stage
     * @throws IOException           Fx exceptions
     * @throws IllegalStateException If app was initialized
     */
    public static Stage init(Stage stage) throws IOException {

        if (instance != null) {
            throw new IllegalStateException("App already started");
        }

        FXMLLoader fxmlLoader = FxKit.getLoader("/ui/view/main.fxml");

        stage.setTitle(i18n("app.title"));
        stage.setResizable(true);
        stage.setWidth(800);
        stage.setHeight(600);

        instance = new App(stage, new Scene(fxmlLoader.load()));

        return stage;
    }

    private final Stage stage;
    private final Scene scene;

    private App(Stage stage, Scene scene) {

        this.stage = stage;
        this.scene = scene;
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }
}
