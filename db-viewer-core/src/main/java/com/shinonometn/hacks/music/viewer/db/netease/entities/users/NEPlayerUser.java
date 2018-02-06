package com.shinonometn.hacks.music.viewer.db.netease.entities.users;

import com.shinonometn.hacks.music.viewer.info.PlayerUser;
import lombok.Data;

import java.util.List;

@Data
public class NEPlayerUser implements PlayerUser {
    private Integer id;
    private List<Integer> playListIds;

    @Override
    public String getAccount() {
        return String.valueOf(id);
    }
}
