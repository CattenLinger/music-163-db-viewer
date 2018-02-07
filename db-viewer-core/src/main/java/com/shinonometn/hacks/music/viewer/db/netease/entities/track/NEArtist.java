package com.shinonometn.hacks.music.viewer.db.netease.entities.track;

/**
 * Created by JacksonGenerator on 1/31/18.
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shinonometn.hacks.music.viewer.info.Artist;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NEArtist implements Artist{
    @JsonProperty("name")
    private String name;

    @JsonProperty("tns")
    private List tns;

    @JsonProperty("alias")
    private List<String> alias;

    @JsonProperty("id")
    private Integer id;

    @Override
    public Integer getUnifiedId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getAlias() {
        return alias;
    }
}