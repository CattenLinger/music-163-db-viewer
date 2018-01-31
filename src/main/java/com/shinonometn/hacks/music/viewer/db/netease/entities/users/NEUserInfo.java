package com.shinonometn.hacks.music.viewer.db.netease.entities.users;

import com.shinonometn.hacks.music.viewer.info.UserInfo;
import lombok.Data;

import java.util.List;

@Data
public class NEUserInfo implements UserInfo {
    private Integer id;
    private List<Integer> playlistIds;

    @Override
    public Integer getUnifiedId() {
        return id;
    }

    @Override
    public List<Integer> getPlayListIds() {
        return playlistIds;
    }
}
