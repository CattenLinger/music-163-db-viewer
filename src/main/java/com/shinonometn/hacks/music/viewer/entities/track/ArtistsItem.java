package com.shinonometn.hacks.music.viewer.entities.track;

/**
 * Created by JacksonGenerator on 1/31/18.
 */

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class ArtistsItem {
    @JsonProperty("name")
    private String name;

    @JsonProperty("tns")
    private List tns;

    @JsonProperty("alias")
    private List alias;

    @JsonProperty("id")
    private Integer id;
}