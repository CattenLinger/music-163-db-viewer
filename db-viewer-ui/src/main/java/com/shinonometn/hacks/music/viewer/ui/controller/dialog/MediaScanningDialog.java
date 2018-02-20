package com.shinonometn.hacks.music.viewer.ui.controller.dialog;

import com.shinonometn.hacks.music.viewer.util.FxKit;
import com.shinonometn.hacks.music.viewer.util.I18n;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

/**
 * Created by cattenlinger on 2018/2/20.
 */
public class MediaScanningDialog extends VBox {

    @FXML
    private Label labelScanningDirectory;

    @FXML
    private Label labelCurrentItem;

    @FXML
    private ProgressBar progressScanning;

    @FXML
    private Button btnInteractive;

    public MediaScanningDialog() {
        FxKit.load(this,"/ui/view/dialogs/mediaScanning.fxml");
    }

    @FXML
    private void initialize(){
        btnInteractive.setText(I18n.i18n("mediaScanning.button.cancel"));
    }

    public void setTask(){
        // TODO
    }
}
