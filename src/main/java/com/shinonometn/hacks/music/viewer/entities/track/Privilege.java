package com.shinonometn.hacks.music.viewer.entities.track;

/**
 * Created by JacksonGenerator on 1/31/18.
 */

import com.fasterxml.jackson.annotation.JsonProperty;


public class Privilege {
    @JsonProperty("flag")
    private Integer flag;
    @JsonProperty("fee")
    private Integer fee;
    @JsonProperty("maxDownBr")
    private Integer maxDownBr;
    @JsonProperty("maxFreeBr")
    private Integer maxFreeBr;
    @JsonProperty("maxPlayBr")
    private Integer maxPlayBr;
    @JsonProperty("version")
    private String version;
    @JsonProperty("commentPriv")
    private Integer commentPriv;
    @JsonProperty("subPriv")
    private Integer subPriv;
    @JsonProperty("toast")
    private Boolean toast;
    @JsonProperty("cloudSong")
    private Integer cloudSong;
    @JsonProperty("maxSongBr")
    private Integer maxSongBr;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("sharePriv")
    private Integer sharePriv;
    @JsonProperty("payed")
    private Integer payed;
    @JsonProperty("status")
    private Integer status;
}