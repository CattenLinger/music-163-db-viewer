package com.shinonometn.hacks.music.viewer.ui.controller.dialog;

import javafx.scene.control.Alert;

import static com.shinonometn.hacks.music.viewer.util.I18n.i18n;

public class SessionInfoDialog extends Alert {
    public SessionInfoDialog(String content) {
        super(AlertType.INFORMATION);

        setTitle(i18n("dialog.sessionInfo.title"));
        setHeaderText(i18n("dialog.sessionInfo.header"));

        setContentText(content);
    }
}
