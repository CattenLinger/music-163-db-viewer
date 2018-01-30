package com.shinonometn.hacks.music.viewer.entities.track;

/**
 * Created by JacksonGenerator on 1/31/18.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Album {
    @JsonProperty("picUrl")
    private String picUrl;

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("picId")
    private Long picId;
}