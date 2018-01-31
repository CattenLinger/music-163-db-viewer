package com.shinonometn.hacks.music.viewer.info;

import java.util.List;

public interface Artist {
    Integer getUnifiedId();
    String getName();
    List<String> getAlias();
}
