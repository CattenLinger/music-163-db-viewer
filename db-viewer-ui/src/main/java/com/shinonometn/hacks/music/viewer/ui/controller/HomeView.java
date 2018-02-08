package com.shinonometn.hacks.music.viewer.ui.controller;

import com.shinonometn.hacks.music.viewer.Main;
import com.shinonometn.hacks.music.viewer.commons.ui.SectionTab;
import com.shinonometn.hacks.music.viewer.db.MusicRepo;
import com.shinonometn.hacks.music.viewer.db.netease.NeteaseMusicRepoFactory;
import com.shinonometn.hacks.music.viewer.ui.App;
import com.shinonometn.hacks.music.viewer.ui.controller.database.DatabaseView;
import com.shinonometn.hacks.music.viewer.util.FxKit;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.function.Consumer;

import static com.shinonometn.hacks.music.viewer.util.I18n.i18n;

public class HomeView extends BorderPane {

    private final static HomeView instance = new HomeView();

    public static HomeView instance() {
        return instance;
    }

    /*
    *
    *
    *
    * */

    @FXML
    private MenuItem menuItemOpenDatabase;

    @FXML
    private TabPane tabPaneMain;

    private HomeView() {
        FxKit.load(this, "/ui/view/main.fxml");

        postInitialize();
    }

    @FXML
    private void initialize() {
        menuItemOpenDatabase.setOnAction(e -> createDatabaseView());
    }

    private void postInitialize(){
        addNewTab(new DatabaseView(), e -> { });
    }

    /**
     *
     * Add a new tab that cannot be close
     * Tab name will auto-fill with @SectionTab
     *
     * @param tabContent Content of the tab
     */
    public void addNewTab(Node tabContent){
        addNewTab(tabContent, (Consumer<Event>) null);
    }

    /**
     *
     * Add a new tab that cannot be close
     * Using given title as the tab title
     *
     * @param tabContent Content of the tab
     * @param title Title
     */
    public void addNewTab(Node tabContent, String title){
        addNewTab(tabContent,title,null);
    }

    /**
     * Add a new closable tab
     *
     * @param tabContent Content of tab
     * @param action action after close
     */
    public void addNewTab(Node tabContent, Consumer<Event> action){
        String title;
        SectionTab sectionTab = tabContent.getClass().getAnnotation(SectionTab.class);
        if(sectionTab != null) {
            title = i18n(sectionTab.value());
        }else{
            title = i18n("tab.new");
        }

        addNewTab(tabContent,title,action);
    }

    /**
     *
     * Adda a new Tab
     *
     * @param tabContent Content of tab
     * @param title Title
     * @param action action after close, if null, the tab will not be closable
     */
    public void addNewTab(Node tabContent, String title, Consumer<Event> action){
        Tab tab = new Tab();
        tab.setText(title);
        if(action != null){
            tab.setOnClosed(action::accept);
            tab.setClosable(true);
        }else{
            tab.setClosable(false);
        }
        tab.setContent(tabContent);
        tabPaneMain.getTabs().add(tab);
        tabPaneMain.getSelectionModel().select(tab);
    }

    public void createDatabaseView(DatabaseView targetDBView) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(App.stage());

        if (selectedFile != null) {
            try {
                MusicRepo musicRepo = new NeteaseMusicRepoFactory(selectedFile.getAbsolutePath()).build();
                targetDBView.changeMusicRepo(musicRepo);
            } catch (Exception e) {
                Main.LOGGER.error("", e);
                new Alert(Alert.AlertType.ERROR, i18n("msg.create_session_view_failed"), ButtonType.OK).showAndWait();
            }
        }
    }

    public void createDatabaseView() {
        DatabaseView databaseView = new DatabaseView();
        addNewTab(databaseView, e -> { });
        createDatabaseView(databaseView);
    }
}
