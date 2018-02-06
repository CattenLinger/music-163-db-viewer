package com.shinonometn.hacks.music.viewer.info;

import java.io.Serializable;

public interface PlayerUser extends Serializable{

    default String getAccount() {
        return "info.unknown";
    }
}
