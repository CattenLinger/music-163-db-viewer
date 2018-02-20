package com.shinonometn.hacks.music.viewer.info.query;

import java.util.Map;

/**
 * Created by cattenlinger on 2018/2/20.
 */
public interface PageRequest {

    enum SortingOrder {
        ASC, DESC
    }

    Integer page();
    Integer size();
    Map<String,SortingOrder> sort();
}
