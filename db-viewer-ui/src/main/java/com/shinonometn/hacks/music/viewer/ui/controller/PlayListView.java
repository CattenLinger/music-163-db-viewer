package com.shinonometn.hacks.music.viewer.ui.controller;

import com.shinonometn.hacks.music.viewer.info.PlayList;
import com.shinonometn.hacks.music.viewer.info.TrackInfo;
import com.shinonometn.hacks.music.viewer.util.FxKit;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.util.List;
import java.util.function.Supplier;

public class PlayListView extends BorderPane {

    private PlayList playList;
    private List<TrackInfo> trackInfos;

    @FXML
    private Label labelPlayListTitle;

    @FXML
    private Label labelPlayListDescription;

    @FXML
    private TableView<TrackInfo> tableTracks;
    private ObservableList<TrackInfo> trackInfoList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<TrackInfo, String> columnTitle;

    @FXML
    private TableColumn<TrackInfo, String> columnArtist;

    @FXML
    private TableColumn<TrackInfo, String> columnAlbum;

    public PlayListView(PlayList playList, List<TrackInfo> trackInfos) {
        this.playList = playList;
        this.trackInfos = trackInfos;

        FxKit.load(this, "/ui/view/playListView.fxml");
    }

    @FXML
    private void initialize() {
        labelPlayListTitle.setText(playList.getTitle());
        labelPlayListDescription.setText(playList.getDescription());

        columnTitle.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getName()));
        columnAlbum.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getAlbum().getName()));
        columnArtist.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(((Supplier<String>) () -> {
            StringBuilder s = new StringBuilder();
            data.getValue().getArtist().forEach(i -> s.append(i.getName()).append(" "));
            return s.toString();
        }).get()));

        tableTracks.setItems(trackInfoList);
        trackInfoList.addAll(trackInfos);
    }
}
