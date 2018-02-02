package com.shinonometn.hacks.music.viewer.db.netease.entities.track;

/**
 * Created by JacksonGenerator on 1/31/18.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shinonometn.hacks.music.viewer.info.Album;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NEAlbum implements Album {
    @JsonProperty("picUrl")
    private String picUrl;

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("picId")
    private Long picId;

    @Override
    public Integer getUnifiedId() {
        return id;
    }

    @Override
    public List<String> getCovers() {
        ArrayList<String> list = new ArrayList<>(1);
        if (picUrl != null) list.add(picUrl);
        return list;
    }
}