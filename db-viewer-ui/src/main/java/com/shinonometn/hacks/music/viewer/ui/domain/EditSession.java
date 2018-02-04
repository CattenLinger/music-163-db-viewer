package com.shinonometn.hacks.music.viewer.ui.domain;

import com.shinonometn.hacks.music.viewer.db.MusicRepo;

public class EditSession {
    private MusicRepo musicRepo;

    public EditSession(MusicRepo musicRepo){
        this.musicRepo = musicRepo;
    }
}
