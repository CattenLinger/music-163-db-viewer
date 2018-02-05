package com.shinonometn.hacks.music.viewer.info;

import java.io.Serializable;

public interface PlayList extends Serializable{

    default String getTitle(){
        return "playlist.name.unknown";
    }

    default String getDescription(){
        return "";
    }

}
