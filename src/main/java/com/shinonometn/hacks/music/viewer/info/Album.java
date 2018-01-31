package com.shinonometn.hacks.music.viewer.info;

import java.util.List;

public interface Album {
    Integer getUnifiedId();
    String getName();
    List<String> getCovers();
}
