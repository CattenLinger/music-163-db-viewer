package com.shinonometn.hacks.music.viewer.ui.controller.database;

import com.shinonometn.hacks.music.viewer.commons.ui.SectionTab;
import com.shinonometn.hacks.music.viewer.db.MusicRepo;
import com.shinonometn.hacks.music.viewer.ui.controller.HomeView;
import com.shinonometn.hacks.music.viewer.util.FxKit;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

@SectionTab("%tab.database.title")
public class DatabaseView extends BorderPane{

    public DatabaseView() {
        this(null);
    }

    public DatabaseView(MusicRepo musicRepo){
        FxKit.load(this,"/ui/view/databaseView.fxml");

        if(musicRepo == null){
            ExplorerWelcomeView explorerWelcomeView = new ExplorerWelcomeView();
            explorerWelcomeView.setButtonAction(e -> HomeView.instance().createDatabaseView(this));
            this.setCenter(explorerWelcomeView);
        }else{
            this.setCenter(new DatabaseExplorerView(musicRepo));
        }
    }

    @FXML
    private void initialize(){

    }


    /**
     *
     * Show a MusicRepo view at center
     *
     * If there is an DatabaseExplorerView at center, change it's MusicRepo.
     * If not, create an DatabaseExplorerView and place it at center.
     *
     * @param musicRepo MusicRepo
     */
    public void changeMusicRepo(MusicRepo musicRepo){
        Node center = this.getCenter();
        DatabaseExplorerView databaseExplorerView;
        if(center instanceof DatabaseExplorerView){
            databaseExplorerView = (DatabaseExplorerView) center;
        }else{
            databaseExplorerView = new DatabaseExplorerView(musicRepo);
            this.setCenter(databaseExplorerView);
            return;
        }

        databaseExplorerView.changeMusicRepo(musicRepo);
    }
}
