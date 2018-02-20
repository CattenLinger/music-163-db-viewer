package com.shinonometn.hacks.music.viewer.ui.controller.dialog;

import com.shinonometn.hacks.music.viewer.info.PlayerUser;
import com.shinonometn.hacks.music.viewer.ui.component.TextPropertyListCell;
import com.shinonometn.hacks.music.viewer.util.FxKit;
import com.shinonometn.hacks.music.viewer.util.I18n;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class SelectUserDialog extends VBox {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnConfirm;

    @FXML
    private ListView<PlayerUser> listUser;

    private ObservableList<PlayerUser> container = FXCollections.observableArrayList();

    public SelectUserDialog() {
        FxKit.load(this, "/ui/view/dialogs/selectUser.fxml");
    }

    @FXML
    private void initialize() {

        listUser.setCellFactory(param -> TextPropertyListCell.of(PlayerUser::getAccount));
        listUser.setItems(container);

        //Change the confirm button's status when list item changed.
        listUser.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> checkBtn());

        //Install tooltips
        Tooltip.install(btnCancel, new Tooltip(I18n.i18n("selectUser.btnCancel.tooltip")));
        Tooltip.install(btnConfirm, new Tooltip(I18n.i18n("selectUser.btnConfirm.tooltip")));

        checkBtn();
    }

    private void setUp(List<PlayerUser> users) {
        container.clear();
        container.addAll(users);

        if (users.size() > 0) {
            //Select the first item in default.
            listUser.getSelectionModel().select(0);
        }

        checkBtn();
    }

    private void checkBtn() {
        btnConfirm.setDisable(listUser.getFocusModel().getFocusedItem() == null);
    }

    public PlayerUser show(Stage parent, List<PlayerUser> listContent) {

        setUp(listContent);

        Stage stage = new Stage();
        stage.setTitle(I18n.i18n("selectUser.window.title"));
        stage.initOwner(parent);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(this));

        //Set up buttons
        btnConfirm.setOnAction(e -> onConfirm(stage));
        btnCancel.setOnAction(e -> onCancel(stage));

        stage.setOnCloseRequest(e -> onCancel(stage));

        listUser.setOnMouseClicked(click -> {
            if(click.getClickCount() == 2){
                stage.close();
            }
        });

        stage.showAndWait();

        return listUser.getSelectionModel().getSelectedItem();
    }

    private void onConfirm(Stage stage){
        stage.close();
    }

    private void onCancel(Stage stage){
        //Cancel selected
        listUser.getSelectionModel().select(-1);
        stage.close();
    }
}
