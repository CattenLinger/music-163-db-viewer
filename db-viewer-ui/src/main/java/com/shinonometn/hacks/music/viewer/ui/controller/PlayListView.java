package com.shinonometn.hacks.music.viewer.ui.controller;

import com.shinonometn.hacks.music.viewer.info.PlayList;
import com.shinonometn.hacks.music.viewer.info.TrackInfo;
import com.shinonometn.hacks.music.viewer.util.FxKit;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class PlayListView extends BorderPane {

    //Current playlist
    private Property<PlayList> playList = new SimpleObjectProperty<>();

    @FXML
    private Label labelPlayListTitle;

    @FXML
    private Label labelPlayListDescription;

    @FXML
    private TableView<TrackInfo> tableTracks;
    //Current playlist's tracks
    private ObservableList<TrackInfo> trackInfoList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<TrackInfo, String> columnTitle;

    @FXML
    private TableColumn<TrackInfo, String> columnArtist;

    @FXML
    private TableColumn<TrackInfo, String> columnAlbum;

    private Function<PlayList,List<TrackInfo>> trackInfoFunction;

    /**
     *
     * Create PlayList view
     *
     * @param trackInfoFunction function for querying TrackInfo
     */
    public PlayListView(Function<PlayList,List<TrackInfo>> trackInfoFunction) {
        FxKit.load(this, "/ui/view/playListView.fxml");

        this.trackInfoFunction = trackInfoFunction;
    }

    @FXML
    private void initialize() {

        playList.addListener((observable, oldValue, newValue) -> {
            labelPlayListTitle.setText(newValue.getTitle());
            labelPlayListDescription.setText(newValue.getDescription());

            this.trackInfoList.clear();
            this.trackInfoList.addAll(trackInfoFunction.apply(newValue));
        });

        // Set up table view
        columnTitle.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getName()));
        columnAlbum.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getAlbum().getName()));
        columnArtist.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(((Supplier<String>) () -> {
            StringBuilder s = new StringBuilder();
            data.getValue().getArtist().forEach(i -> s.append(i.getName()).append(" "));
            return s.toString();
        }).get()));
        tableTracks.setItems(trackInfoList);

    }

    /**
     *
     * Change current playlist
     *
     * @param playList new Playlist
     */
    public void setPlayList(PlayList playList) {
        this.playList.setValue(playList);
    }
}
