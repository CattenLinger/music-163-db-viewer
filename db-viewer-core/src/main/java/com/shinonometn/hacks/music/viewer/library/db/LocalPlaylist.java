package com.shinonometn.hacks.music.viewer.library.db;

import com.shinonometn.hacks.music.viewer.info.PlayList;
import lombok.Data;

/**
 * Created by Catten Linger on 2018/2/21.
 */
@Data
public class LocalPlaylist implements PlayList{

    private Integer id;

    private String title;
    private String descriptions;

}
