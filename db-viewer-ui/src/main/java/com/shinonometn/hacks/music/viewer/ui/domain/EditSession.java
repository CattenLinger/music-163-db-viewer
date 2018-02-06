package com.shinonometn.hacks.music.viewer.ui.domain;

import com.shinonometn.hacks.music.viewer.db.MusicRepo;
import com.shinonometn.hacks.music.viewer.info.PlayerUser;

public class EditSession {
    private MusicRepo musicRepo;
    private PlayerUser playerUser;

    public EditSession(MusicRepo musicRepo, PlayerUser currentPlayerUser){
        this.musicRepo = musicRepo;
        this.playerUser = currentPlayerUser;
    }

    public void close() {

    }
}
