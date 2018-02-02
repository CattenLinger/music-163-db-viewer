package com.shinonometn.hacks.music.viewer;

import com.shinonometn.hacks.music.viewer.util.FxKit;
import com.shinonometn.hacks.music.viewer.util.I18n;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cattenlinger on 2018/1/30.
 */
public class Main extends Application {

    private final static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = FxKit.getLoader("/ui/view/main.fxml");
        BorderPane borderPane = fxmlLoader.load();

        //New an empty view
//        BorderPane borderPane = new BorderPane();

        primaryStage.setTitle(I18n.getString("app.title"));
        primaryStage.setResizable(false);
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        primaryStage.setScene(new Scene(borderPane));
        primaryStage.show();
    }
}
