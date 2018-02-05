package com.shinonometn.hacks.music.viewer.info;

import java.util.List;

public interface TrackInfo {

    Integer getUnifiedId();

    Album getAlbum();

    List<Artist> getArtist();

    String getName();

    List<String> getAlias();

}
