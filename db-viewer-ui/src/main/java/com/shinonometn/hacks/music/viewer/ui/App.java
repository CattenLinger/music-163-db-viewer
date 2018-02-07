package com.shinonometn.hacks.music.viewer.ui;

import com.shinonometn.hacks.music.viewer.ui.controller.HomeView;
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

        stage.setTitle(i18n("app.title"));
        stage.setResizable(true);
        stage.setWidth(1280);
        stage.setHeight(720);

        Scene scene = new Scene(HomeView.instance());
        instance = new App(stage, scene);
        stage.setScene(scene);

        return stage;
    }

    /**
     *
     * The stage of the App
     *
     * @return Stage
     */
    public static Stage stage(){
        return instance.stage;
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
