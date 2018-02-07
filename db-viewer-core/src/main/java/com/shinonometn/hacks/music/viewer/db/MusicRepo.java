package com.shinonometn.hacks.music.viewer.db;

import com.shinonometn.hacks.music.viewer.info.PlayList;
import com.shinonometn.hacks.music.viewer.info.PlayerUser;
import com.shinonometn.hacks.music.viewer.info.TrackInfo;

import java.util.List;

public interface MusicRepo {

    /**
     *
     * Get playlist owner lists
     *
     * Some music player has multi-user supporting.
     * Use this function to list all users. If the target
     * music player of the implement doesn't support multi-user,
     * return a list contains a default user.
     *
     * @return a list of PlayerUser
     */
    List<PlayerUser> getUsers();

    /**
     *
     * Get all lists that owned by a user
     *
     * @param playerUser a user
     * @return a list of PlayList
     */
    List<PlayList> getLists(PlayerUser playerUser);

    /**
     *
     * Get all lists that in database
     *
     * @return a list of PlayList
     */
    List<PlayList> getLists();

    /**
     *
     * Get all tracks that a playlist contains.
     *
     * TrackInfo(s) are sored.
     *
     * @param playList target PlayList
     * @return a list of TrackInfo
     */
    List<TrackInfo> getTracks(PlayList playList);

    /**
     *
     * Return the MusicRepo implement description
     *
     * @return
     */
    default String getProviderName(){
        return this.getClass().getName();
    }
}
