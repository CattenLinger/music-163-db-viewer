package com.shinonometn.hacks.music.viewer.entities.track;

/**
 * Created by JacksonGenerator on 1/31/18.
 */

import com.fasterxml.jackson.annotation.JsonProperty;


public class MMusic {
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("volumeDelta")
    private Double volumeDelta;
    @JsonProperty("bitrate")
    private Integer bitrate;
    @JsonProperty("dfsId")
    private Integer dfsId;
}