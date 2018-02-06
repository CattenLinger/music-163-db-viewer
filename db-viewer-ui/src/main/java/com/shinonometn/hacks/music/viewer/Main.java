package com.shinonometn.hacks.music.viewer;

import com.shinonometn.hacks.music.viewer.ui.App;
import com.shinonometn.hacks.music.viewer.util.AppCrashHandler;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cattenlinger on 2018/1/30.
 */
public class Main extends Application {

    public final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(AppCrashHandler.getInstance());

        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        App.init(primaryStage).show();
    }
}
