package com.shinonometn.hacks.music.viewer.db;

import com.shinonometn.hacks.music.viewer.info.Playlist;
import com.shinonometn.hacks.music.viewer.info.TrackInfo;
import com.shinonometn.hacks.music.viewer.info.UserInfo;

import java.util.List;

public interface MusicRepo {

    List<UserInfo> getUsers();
    List<Playlist> getUserListIds(Integer userId);

    List<Playlist> getAllList();

    Playlist getList(Integer unifiedId);

    List<TrackInfo> getListTracks(Playlist playlist);
}
