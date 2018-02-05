package com.shinonometn.hacks.music.viewer.db.netease.entities.playlist;

/**
 * Created by JacksonGenerator on 1/31/18.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shinonometn.hacks.music.viewer.info.PlayList;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NEPlayList implements PlayList {
    @JsonProperty("privacy")
    private Integer privacy;

    @JsonProperty("description")
    private String description;

    @JsonProperty("trackNumberUpdateTime")
    private Long trackNumberUpdateTime;

    @JsonProperty("subscribed")
    private Boolean subscribed;

    @JsonProperty("adType")
    private Integer adType;

    @JsonProperty("trackCount")
    private Integer trackCount;

    @JsonProperty("coverImgId_str")
    private String coverImgIdStr;

    @JsonProperty("specialType")
    private Integer specialType;

//    @JsonProperty("artists")
//    private JSONObject$Null artists;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("totalDuration")
    private Integer totalDuration;

    @JsonProperty("ordered")
    private Boolean ordered;

    @JsonProperty("creator")
    private NECreator creator;

    @JsonProperty("subscribers")
    private List subscribers;

    @JsonProperty("highQuality")
    private Boolean highQuality;

    @JsonProperty("commentThreadId")
    private String commentThreadId;

    @JsonProperty("updateTime")
    private Long updateTime;

    @JsonProperty("trackUpdateTime")
    private Long trackUpdateTime;

    @JsonProperty("userId")
    private Integer userId;

//    @JsonProperty("tracks")
//    private JSONObject$Null tracks;

    @JsonProperty("tags")
    private List<String> tags;

    @JsonProperty("anonimous")
    private Boolean anonimous;

    @JsonProperty("cloudTrackCount")
    private Integer cloudTrackCount;

    @JsonProperty("coverImgUrl")
    private String coverImgUrl;

    @JsonProperty("playCount")
    private Integer playCount;

    @JsonProperty("coverImgId")
    private Long coverImgId;

    @JsonProperty("createTime")
    private Long createTime;

    @JsonProperty("name")
    private String name;

    @JsonProperty("subscribedCount")
    private Integer subscribedCount;

    @JsonProperty("newImported")
    private Boolean newImported;

    @JsonProperty("status")
    private Integer status;

    @Override
    public String getTitle() {
        return name;
    }
}
