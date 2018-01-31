package com.shinonometn.hacks.music.viewer.db.netease.entities.playlist;

/**
 * Created by JacksonGenerator on 1/31/18.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NECreator {

    @JsonProperty("birthday")
    private Long birthday;

    @JsonProperty("detailDescription")
    private String detailDescription;

    @JsonProperty("backgroundUrl")
    private String backgroundUrl;

    @JsonProperty("gender")
    private Integer gender;

    @JsonProperty("city")
    private Integer city;

    @JsonProperty("signature")
    private String signature;

    @JsonProperty("description")
    private String description;

//    @JsonProperty("remarkName")
//    private JSONObject$Null remarkName;

    @JsonProperty("accountStatus")
    private Integer accountStatus;

    @JsonProperty("avatarImgId")
    private Long avatarImgId;

    @JsonProperty("defaultAvatar")
    private Boolean defaultAvatar;

    @JsonProperty("backgroundImgIdStr")
    private String backgroundImgIdStr;

    @JsonProperty("avatarImgIdStr")
    private String avatarImgIdStr;

    @JsonProperty("province")
    private Integer province;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("expertTags")
    private List<String> expertTags;

    @JsonProperty("djStatus")
    private Integer djStatus;

    @JsonProperty("avatarUrl")
    private String avatarUrl;

    @JsonProperty("authStatus")
    private Integer authStatus;

    @JsonProperty("vipType")
    private Integer vipType;

    @JsonProperty("followed")
    private Boolean followed;

    @JsonProperty("userId")
    private Integer userId;

    @JsonProperty("mutual")
    private Boolean mutual;

    @JsonProperty("avatarImgId_str")
    private String avatarImgId_Str;

    @JsonProperty("authority")
    private Integer authority;

    @JsonProperty("userType")
    private Integer userType;

    @JsonProperty("backgroundImgId")
    private Long backgroundImgId;

//    @JsonProperty("experts")
//    private JSONObject$Null experts;
}
