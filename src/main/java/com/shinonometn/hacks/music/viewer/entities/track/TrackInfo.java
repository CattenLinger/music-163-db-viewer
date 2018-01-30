package com.shinonometn.hacks.music.viewer.entities.track;

/**
 * Created by JacksonGenerator on 1/31/18.
 */

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class TrackInfo {
    @JsonProperty("fee")
    private Integer fee;

    @JsonProperty("privilege")
    private Privilege privilege;

    @JsonProperty("mMusic")
    private MMusic mMusic;

    @JsonProperty("mst")
    private Integer mst;

    @JsonProperty("duration")
    private Integer duration;

    @JsonProperty("artists")
    private List<ArtistsItem> artists;

    @JsonProperty("rtUrls")
    private List rtUrls;

    @JsonProperty("popularity")
    private Integer popularity;

    @JsonProperty("alias")
    private List<String> alias;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("cd")
    private String cd;

    @JsonProperty("pstatus")
    private Integer pstatus;

    @JsonProperty("album")
    private Album album;

    @JsonProperty("lMusic")
    private LMusic lMusic;

    @JsonProperty("commentThreadId")
    private String commentThreadId;

//    @JsonProperty("ringtone")
//    private JSONObject$Null ringtone;

    @JsonProperty("yunSong")
    private YunSong yunSong;

    @JsonProperty("songType")
    private Integer songType;

    @JsonProperty("version")
    private Integer version;

//    @JsonProperty("rtUrl")
//    private JSONObject$Null rtUrl;

    @JsonProperty("ftype")
    private Integer ftype;

    @JsonProperty("copyrightId")
    private Integer copyrightId;

    @JsonProperty("hMusic")
    private HMusic hMusic;

    @JsonProperty("mvid")
    private Integer mvid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("position")
    private Integer position;

    @JsonProperty("status")
    private Integer status;
}