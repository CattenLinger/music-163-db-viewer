package com.shinonometn.hacks.music.viewer.ui.controller;

import com.shinonometn.hacks.music.viewer.util.FxKit;
import com.shinonometn.hacks.music.viewer.utils.FunctionUtils;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class OpenSessionView extends VBox{

    private static OpenSessionView instance = FunctionUtils.invoke(OpenSessionView::new);

    public static OpenSessionView getInstance(){
        return instance;
    }

    public OpenSessionView() throws IOException {
        FxKit.load(this,"/ui/view/openSession.fxml");
    }

}
