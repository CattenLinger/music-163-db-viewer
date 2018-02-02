package com.shinonometn.hacks.music.viewer.db.netease.entities.track;

/**
 * Created by JacksonGenerator on 1/31/18.
 */

import com.fasterxml.jackson.annotation.JsonProperty;


public class YunSong {
    @JsonProperty("songName")
    private String songName;

    @JsonProperty("uid")
    private Integer uid;

    @JsonProperty("coverId")
    private String coverId;

    @JsonProperty("artist")
    private String artist;

    @JsonProperty("album")
    private String album;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("bitrate")
    private Integer bitrate;

    @JsonProperty("fileExt")
    private String fileExt;
}